package com.zheng.es.parser;

import com.zheng.es.builder.FilterQueryBuilder;
import com.zheng.es.utils.IndexConfigUtil;
import com.zheng.es.model.Index;
import com.zheng.es.model.Type;
import com.zheng.es.model.EsPage;
import com.zheng.es.model.EsQuery;
import com.zheng.es.model.QueryParams;
import com.zheng.es.utils.StringUtil;
import org.elasticsearch.index.query.QueryBuilder;
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
    private IndexConfigUtil init = IndexConfigUtil.getInstance();
    
    @Override
    public EsQuery parse(QueryParams params) {
        if (StringUtil.isEmpty(params)) {
            return null;
        }
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
        // 滚动查询
        builder.scrollId(params.getScrollId());
        // preference
        builder.preference(params.getPreference());
        // fields
        builder.fields(params.getFields());
        // filters
        QueryBuilder filterQueryBuilder = new FilterQueryBuilder(params.getFilters(), type).build();
        builder.queryBuilder(filterQueryBuilder);
        // queryScoreEnable
        builder.logEnable(params.isLogEnable());
        // logEnable
        builder.queryScoreEnable(params.isQueryScoreEnable());
        // key
        builder.key(params.getKey());
        EsQuery esQuery = builder.build();
        return esQuery;
    }
}
