package com.zheng.es.pool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  搜索查询线程工厂
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月20日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchThreadFactory implements ThreadFactory {
    private ThreadGroup group;
    private AtomicInteger threadNumber = new AtomicInteger(1);
    private String namePrefix;
    private boolean daemon;
    
    public SearchThreadFactory(String namePrefix) {
        this(namePrefix, true);
    }

    public SearchThreadFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        this.group = (null != s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }
    
    @Override
    public Thread newThread(Runnable r) {
        String threadName = new StringBuilder(namePrefix)
                .append("[T#")
                .append(threadNumber.getAndIncrement())
                .append("]")
                .toString();
        Thread thread = new Thread(group, r, threadName, 0);
        thread.setDaemon(daemon);
        return thread;
    }
}
