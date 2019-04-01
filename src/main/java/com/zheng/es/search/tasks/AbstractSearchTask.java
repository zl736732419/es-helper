package com.zheng.es.search.tasks;

import com.zheng.es.model.Params;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  sku列表查询任务
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class AbstractSearchTask<T> implements ISearchTask<T> {
    private Logger logger = LogManager.getLogger(this.getClass());
    protected Params params;
    protected String taskName;
    
    public AbstractSearchTask(Params params, String taskName) {
        this.params = params;
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public T call() throws Exception {
        T t = null;
        try {
            t = execute();
        } catch (Exception e) {
            logger.error(e);
        }
        return t;
    }
}
