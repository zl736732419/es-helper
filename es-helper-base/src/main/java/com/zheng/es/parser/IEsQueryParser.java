package com.zheng.es.parser;

import com.zheng.es.model.EsQuery;
import com.zheng.es.model.QueryParams;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface IEsQueryParser {
    EsQuery parse(QueryParams params);
}
