package com.zheng.es.builder;

import com.zheng.es.model.Field;
import com.zheng.es.field.CommonField;
import com.zheng.es.field.FilterField;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  terms查询
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsTermsQueryBuilder extends AbstractQueryBuilder {

    public EsTermsQueryBuilder(FilterField filterField, Field field) {
        super(filterField, field);
    }

    public QueryBuilder doBuild(FilterField filterField, Field field) {
        CommonField commonField = (CommonField) filterField;
        QueryBuilder queryBuilder = QueryBuilders.termsQuery(commonField.getField(), commonField.getValues());
        if (field.isNested()) {
            queryBuilder = QueryBuilders.nestedQuery(field.getNestedPath(), queryBuilder, ScoreMode.None);
        }
        return queryBuilder;
    }
}
