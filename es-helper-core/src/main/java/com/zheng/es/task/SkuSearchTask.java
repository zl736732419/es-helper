package com.zheng.es.task;

import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.searcher.ISearcher;
import com.zheng.es.utils.SpringContextUtil;

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
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SkuSearchTask extends AbstractSearchTask<Response> {
    private ISearcher<Response> skuSearcher = SpringContextUtil.getBean("skuSearcher", ISearcher.class);
    
    public SkuSearchTask(Params params, String taskName) {
        super(params, taskName);
    }
    
    @Override
    public Response execute() {
        return skuSearcher.search(params);
    }
}
