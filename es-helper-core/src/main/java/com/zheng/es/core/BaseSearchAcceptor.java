package com.zheng.es.core;

import com.zheng.es.core.post.SearchPostProcessorDispatcher;
import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.task.AbstractSearchTask;
import com.zheng.es.utils.ExceptionUtil;
import com.zheng.es.utils.SignUtil;
import com.zheng.es.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class BaseSearchAcceptor implements ISearchAcceptor<Response> {
    private Logger logger = LogManager.getLogger(this.getClass());
    private ConcurrentHashMap<String, CacheSearchTaskExecutor> cacheExecutor = new ConcurrentHashMap<>();
    
    @Autowired
    private ISearchMapReduce<Response> baseSearchMapReduce;
    @Autowired
    private SearchPostProcessorDispatcher searchPostProcessorDispatcher;
    @Autowired
    private IValidator validator;
    
    @Override
    public Response accept(Params params) throws EsSearchException {
        Response response;
        try {
            // 1. 参数验证
            validate(params);
            // 2. 拆分任务
            List<AbstractSearchTask> tasks = baseSearchMapReduce.map(params);
            // 3. 任务执行
            List<Response> responses = executeTask(tasks, params);
            // 4. 合并结果
            response = baseSearchMapReduce.reduce(responses);
            // 5. 查询后处理
            searchPostProcessorDispatcher.dispatch(params, response);
        } catch (Exception e) {
            return ExceptionUtil.handleException(logger, params, e);
        }
        return response;
    }

    /**
     * 打包执行任务
     * 任务虽然拆分，但是被拆分的任务整体还是属于一次查询，所以将拆分任务打包执行
     * @param tasks
     * @param params
     * @return
     */
    private List<Response> executeTask(List<AbstractSearchTask> tasks, Params params) throws Exception {
        if (StringUtil.isEmpty(tasks)) {
            throw new EsSearchException(EnumExceptionCode.TASK_EMPTY);
        }
        String uniqueKey = SignUtil.uniqueKeyWithPage(params);
        CacheSearchTaskExecutor executor = cacheExecutor.get(uniqueKey);
        if (null != executor) {
            ReentrantLock lock = executor.getLock();
            lock.lock();
            try {
                Condition condition = lock.newCondition();
                executor.addCondition(condition);
                condition.await(3000L, TimeUnit.MILLISECONDS);
                executor.removeCondition(condition);
            } finally {
                lock.unlock();
            }
        } else {
            executor = new CacheSearchTaskExecutor();
            for (AbstractSearchTask task : tasks) {
                if (StringUtil.isEmpty(task)) {
                    continue;
                }
                executor.join(task);
            }
            CacheSearchTaskExecutor cacheSearchTaskExecutor = cacheExecutor.putIfAbsent(uniqueKey, executor);
            if (null != cacheSearchTaskExecutor) {
                executor = cacheSearchTaskExecutor;
            }
        }
        return executor.get();
    }

    private void validate(Params params) {
        validator.validate(params);
        
    }
}
