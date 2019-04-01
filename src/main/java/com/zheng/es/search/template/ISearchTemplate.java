package com.zheng.es.search.template;

import com.zheng.es.exceptions.SearchException;
import com.zheng.es.model.Params;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询模板
 *      置顶查询?
 *      缓存查询?
 *      id查询?
 *      rerank?...
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ISearchTemplate<T> {
    T search(Params params) throws SearchException;
}
