package com.zheng.es.builder;

import com.zheng.es.model.Field;
import com.zheng.es.model.Type;
import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.field.FilterField;
import com.zheng.es.utils.ExceptionUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.StringUtils;

import java.util.List;

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
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public class FilterQueryBuilder {
    
    private List<FilterField> filters;
    private Type type;
    
    public FilterQueryBuilder(List<FilterField> filters, Type type) {
        this.filters = filters;
        this.type = type;
    }
    
    /**
     * 过滤条件构造
     * @return
     */
    public QueryBuilder build() {
        if (StringUtils.isEmpty(filters)) {
            return null;
        }
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        Field field;
        for (FilterField filter : filters) {
            field = type.getField(filter.getField());
            buildFilterQueryBuilder(queryBuilder, filter, field);
        }
        
        return queryBuilder;
    }

    private static void buildFilterQueryBuilder(BoolQueryBuilder resultQueryBuilder, FilterField filter, Field field) {
        QueryBuilder queryBuilder; 
        switch (filter.getFieldType()) {
            case COMMON:
                queryBuilder = new EsTermsQueryBuilder(filter, field).build();
                break;
                // TODO
            default:
                throw new EsSearchException(EnumExceptionCode.UNKNOWN_FIELD_TYPE.getKey(),
                        "unknown field type for field: " + filter.getField());
        }
        if (null == queryBuilder) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.QUERY_BUILDER_EMPTY, null);
        }
        resultQueryBuilder.must(queryBuilder);
    }
}
