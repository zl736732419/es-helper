package com.zheng.es.service;

import com.zheng.es.model.EsQuery;
import com.zheng.es.model.EsSearchResponse;

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
public interface IEsSearcher {
    EsSearchResponse search(EsQuery esQuery);
}
