package com.zheng.es.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
 *  2019年03月20日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchExecutors {
    private static final String THREAD_GROUP_NAME = "search";

    /**
     * 创建线程池
     * @param poolSize
     * @param capacity
     * @return
     */
    public static ThreadPoolExecutor newFixed(int poolSize, int capacity) {
        BlockingQueue<Runnable> workQueue;
        if (capacity <= 0) { // 无界
            workQueue = new LinkedTransferQueue<>();
        } else { // 有界
            workQueue = new SizedBlockingQueue<>(new LinkedTransferQueue(), capacity);
        }
        return new SearchThreadPoolExecutor(THREAD_GROUP_NAME, poolSize, poolSize, 0, TimeUnit.SECONDS, 
                workQueue, new SearchThreadFactory(THREAD_GROUP_NAME), new SearchRejectedExecutionHandler());
    }
    
}
