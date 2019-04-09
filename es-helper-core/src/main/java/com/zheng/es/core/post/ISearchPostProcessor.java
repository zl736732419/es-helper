package com.zheng.es.core.post;

import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Params;

import java.util.Map;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询后期处理
 *  比如高亮、
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ISearchPostProcessor {
    ISearchPostProcessor next();
    void process(Params params, Map<String, Object> data) throws EsSearchException;
}
