package com.zheng.es.search.core;

import com.zheng.es.model.Params;
import com.zheng.es.search.tasks.AbstractSearchTask;

import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  任务拆分合并
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ISearchMapReduce<T> {
    /**
     * 根据参数拆分多任务
     * @param params
     * @return
     */
    List<AbstractSearchTask> map(Params params);

    /**
     * 多任务结果合并
     * @param responses
     * @return
     */
    T reduce(List<T> responses);
}
