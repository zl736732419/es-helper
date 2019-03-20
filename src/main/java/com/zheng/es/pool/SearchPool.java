package com.zheng.es.pool;

import java.util.concurrent.ExecutorService;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询线程池
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月20日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchPool {
    /**
     * 线程池
     */
    private ExecutorService executor;
    /**
     * 队列大小
     */
    private static final Integer queueSize = 1000;
    
    private SearchPool() {
        initExecutorService();
    }

    private void initExecutorService() {
        int threadNum = getThreadNum();
        executor = SearchExecutors.newFixed(threadNum, queueSize);
    }
    
    private int getThreadNum() {
        int availableProcessors = Math.min(32, Runtime.getRuntime().availableProcessors());
        return ((availableProcessors * 3) / 2) + 1;
    }

    private static class Inner {
        private static SearchPool instance = new SearchPool();
    }
    
    public static SearchPool getInstance() {
        return Inner.instance;
    }
    
    public ExecutorService getExecutor() {
        return executor;
    }
}
