package com.zheng.es.search.tasks;

import com.zheng.es.enums.EnumSearchTask;
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
    @Override
    public Response execute() {
        // TODO
        return null;
    }

    @Override
    public String name() {
        return EnumSearchTask.SKU.name();
    }
}
