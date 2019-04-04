package com.zheng.es.searcher;

import com.zheng.es.exceptions.SearchException;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
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
    
    
    @Override
    public Response search(Params params) throws SearchException {
        
        return null;
    }
}
