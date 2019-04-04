package com.zheng.es.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
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
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchThreadPoolExecutor extends ThreadPoolExecutor {
    private String name;
    public SearchThreadPoolExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, 
                                    RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.name = name;
    }

    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        b.append(getClass().getSimpleName()).append('[');
        b.append(name).append(", ");
        if (getQueue() instanceof SizedBlockingQueue)
        {
            @SuppressWarnings("rawtypes")
            SizedBlockingQueue queue = (SizedBlockingQueue) getQueue();
            b.append("queue capacity = ").append(queue.getCapacity()).append(", ");
        }
        b.append(super.toString()).append(']');
        return b.toString();
    }
}
