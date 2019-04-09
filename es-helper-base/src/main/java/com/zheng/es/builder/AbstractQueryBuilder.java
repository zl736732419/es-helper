package com.zheng.es.builder;

import com.zheng.es.model.Field;
import com.zheng.es.field.FilterField;
import com.zheng.es.utils.StringUtil;
import org.elasticsearch.index.query.QueryBuilder;

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
public abstract class AbstractQueryBuilder implements IQueryBuilder {
    protected FilterField filterField;
    protected Field field;
    
    public AbstractQueryBuilder(FilterField filterField, Field field) {
        this.filterField = filterField;
        this.field = field;
    }
    
    @Override
    public QueryBuilder build() {
        if (StringUtil.isEmpty(filterField) || StringUtil.isEmpty(field)) {
            return null;
        }
        return doBuild(filterField, field);
    }

    protected abstract QueryBuilder doBuild(FilterField filterField, Field field);
}
