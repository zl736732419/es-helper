package com.zheng.es.core;

import com.zheng.es.model.Response;
import com.zheng.es.pool.SearchPool;
import com.zheng.es.task.AbstractSearchTask;
import com.zheng.es.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  可缓存的任务执行器
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public class CacheSearchTaskExecutor {
    private Logger logger = LogManager.getLogger(this.getClass());
    /**
     * 任务列表
     */
    private ConcurrentHashMap<String, CompletableFuture<Response>> cacheTask = new ConcurrentHashMap<>();
    /**
     * 线程池
     */
    private ExecutorService executor = SearchPool.getInstance().getExecutor();

    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 阻塞等待条件
     */
    private List<Condition> conditions = new LinkedList<>();
    
    /**
     * 添加任务
     * @param task
     */
    public void join(AbstractSearchTask task) {
        if (StringUtil.isEmpty(task)) {
            return;
        }
        CompletableFuture<Response> future = CompletableFuture.supplyAsync(() -> doExecute(task), executor);
        cacheTask.putIfAbsent(task.getTaskName(), future);
    }

    /**
     * 执行任务
     * @param task
     * @return
     */
    private Response doExecute(AbstractSearchTask task) {
        try {
            return task.call();
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * 执行任务获取结果
     * @return
     */
    public List<Response> actionGet() {
        Collection<CompletableFuture<Response>> futures = cacheTask.values();
        CompletableFuture<List<Response>> resultFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
        List<Response> responses = null;
        try {
            responses = resultFuture.get();
        } catch (Exception e) {
            logger.error("run search task error.", e);
        }
        // 唤醒等待线程
        notifyRequest();
        return responses;
    }

    private void notifyRequest() {
        if (StringUtil.isEmpty(conditions)) {
            return;
        }
        lock.lock();
        try {
            Iterator<Condition> iterator = conditions.iterator();
            Condition condition;
            while (iterator.hasNext()) {
                condition = iterator.next();
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public void removeCondition(Condition condition) {
        if (null != condition) {
            conditions.remove(condition);
        }
    }
    
    public void addCondition(Condition condition) {
        if (null != condition) {
            conditions.add(condition);
        }
    }
}
