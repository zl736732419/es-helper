package com.zheng.es.base;

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
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月12日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface IBaseSearcher {
    Response search(Params params);
}
