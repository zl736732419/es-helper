package com.zheng.es.pool;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.LongAdder;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  任务拒绝策略
 *  不允许拒绝任务，直接抛出异常
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月20日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchRejectedExecutionHandler implements RejectedExecutionHandler {

    /**
     * 已经被拒绝的任务数
     */
    private LongAdder rejected = new LongAdder();
    
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        rejected.add(1L);
        throw new RejectedExecutionException("rejected execution of " + r + " on " + executor + ", have rejected " 
                + rejected.sum() + " tasks now");
    }
    
    
}
