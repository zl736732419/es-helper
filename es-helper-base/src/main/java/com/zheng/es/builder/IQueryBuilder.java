package com.zheng.es.builder;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface IQueryBuilder {
    QueryBuilder build();
}
