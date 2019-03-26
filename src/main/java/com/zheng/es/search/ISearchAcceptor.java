package com.zheng.es.search;

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
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ISearchAcceptor<T> {
    T accept(Params params) throws SearchException;
}
