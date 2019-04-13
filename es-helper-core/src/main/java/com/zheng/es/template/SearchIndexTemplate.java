package com.zheng.es.template;

import com.zheng.es.base.IBaseSearcher;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  基础查询模板
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class SearchIndexTemplate implements ISearchTemplate<Response> {
    @Autowired
    private IBaseSearcher baseSearcher;
    
    @Override
    public Response search(Params params) throws EsSearchException {
        Response response = baseSearcher.search(params);
        return response;
    }
}
