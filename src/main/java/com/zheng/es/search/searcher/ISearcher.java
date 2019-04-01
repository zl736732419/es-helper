package com.zheng.es.search.searcher;

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
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ISearcher<T> {
    T search(Params params) throws SearchException;
}
