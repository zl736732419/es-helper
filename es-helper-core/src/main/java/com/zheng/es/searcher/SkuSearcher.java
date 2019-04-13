package com.zheng.es.searcher;

import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.template.ISearchTemplate;
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
 *  sku列表查询
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class SkuSearcher implements ISearcher<Response> {
    
    @Autowired
    private ISearchTemplate<Response> searchIndexTemplate;
    
    @Override
    public Response search(Params params) throws EsSearchException {
        return searchIndexTemplate.search(params);
    }
}
