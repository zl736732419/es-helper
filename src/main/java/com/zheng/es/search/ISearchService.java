package com.zheng.es.search;

import com.zheng.es.model.Params;
import com.zheng.es.model.Response;

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
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ISearchService {
    Response search(Params params) throws Exception;
}
