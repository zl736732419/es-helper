package com.zheng.es.search.core.parser;

import com.zheng.es.config.model.Index;
import com.zheng.es.config.model.Type;
import com.zheng.es.config.util.ConfigInit;
import com.zheng.es.model.Params;
import com.zheng.es.search.core.model.EsPage;
import com.zheng.es.search.core.model.EsQuery;
import org.springframework.stereotype.Component;

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
 *  2019年04月02日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class BaseEsQueryParser implements IEsQueryParser {
    private ConfigInit init = ConfigInit.getInstance();
    @Override
    public EsQuery parse(Params params) {
        EsQuery.Builder builder = new EsQuery.Builder();
        // domain
        String domain = params.getDomain();
        builder.index(domain);
        // type
        Index index = init.getIndex(domain);
        Type type = index.getType(params.getType());
        builder.type(type.getName());
        // 分页信息
        EsPage esPage = new EsPage.Builder()
                .pageNo(params.getPageNo())
                .size(params.getPageSize())
                .build();
        builder.page(esPage);
        
        
        return null;
    }
}
