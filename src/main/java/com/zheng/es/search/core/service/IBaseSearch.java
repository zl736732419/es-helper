package com.zheng.es.search.core.service;

import com.zheng.es.search.core.model.EsQuery;
import org.elasticsearch.action.search.SearchResponse;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  ES原生查询接口
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface IBaseSearch {
    SearchResponse search(EsQuery esQuery) throws Exception;
}
