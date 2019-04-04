package com.zheng.es.search.core.parser;

import com.zheng.es.model.Params;
import com.zheng.es.search.core.model.EsQuery;

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
    EsQuery parse(Params params);
}
