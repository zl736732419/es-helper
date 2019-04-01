package com.zheng.es.search.tasks;

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
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class SkuSearchTask extends AbstractSearchTask<Response> {
    public SkuSearchTask(Params params, String taskName) {
        super(params, taskName);
    }
    
    @Override
    public Response execute() {
        // TODO
        return null;
    }
}
