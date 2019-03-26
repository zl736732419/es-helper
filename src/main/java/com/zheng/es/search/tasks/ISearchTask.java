package com.zheng.es.search.tasks;

import java.util.concurrent.Callable;

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
public interface ISearchTask<T> extends Callable<T> {
    /**
     * 任务名称
     * @see com.zheng.es.enums.EnumSearchTask
     * @return
     */
    String name();
}
