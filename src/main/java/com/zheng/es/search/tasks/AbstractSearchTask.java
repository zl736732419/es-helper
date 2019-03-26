package com.zheng.es.search.tasks;

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
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class AbstractSearchTask<T> implements ISearchTask<T> {
    private Logger logger = LogManager.getLogger(this.getClass());
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

    public abstract T execute();
}
