package com.zheng.es.model;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.suggest.SuggestBuilder;

import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  ES查询
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月01日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsQuery {
    /**
     * 索引名
     */
    private String index;
    /**
     * 索引type, v6之后不允许一个index存在多个type
     */
    private String type;
    /**
     * 分页页码
     */
    private Integer from;
    /**
     * 分页大小
     */
    private Integer size;
    /**
     * 滚动查询
     */
    private String scrollId;
    /**
     * 查询偏好，设置preference后基于相同preference的查询会落到同一个shard上
     */
    private String preference;
    /**
     * 召回的字段
     */
    private List<String> fields;
    /**
     * 查询条件
     */
    private QueryBuilder queryBuilder;
    /**
     * 聚合条件
     */
    private List<AbstractAggregationBuilder> aggregationBuilders;
    /**
     * 显示排序字段 
     */
    private List<SortBuilder> sorts;
    /**
     * 过滤去重
     */
    private CollapseBuilder collapseBuilder;
    /**
     * 自动补全
     */
    private SuggestBuilder suggestBuilder;
    /**
     * 日志信息
     */
    private boolean logEnable;
    /**
     * 是否sku展示得分
     */
    private boolean queryScore;

    public static class Builder {
        private EsQuery query;
        public Builder() {
            query = new EsQuery();
        }
        
        public Builder index(String index) {
            query.setIndex(index);
            return this;
        }

        public Builder type(String type) {
            query.setType(type);
            return this;
        }
        
        public Builder scrollId(String scrollId) {
            query.setScrollId(scrollId);
            return this;
        }

        public Builder page(EsPage esPage) {
            query.setFrom(esPage.getFrom());
            query.setSize(esPage.getSize());
            return this;
        }
        
        public Builder logEnable(boolean logEnable) {
            query.setLogEnable(logEnable);
            return this;
        }

        public Builder queryScore(boolean queryScore) {
            query.setQueryScore(queryScore);
            return this;
        }

        public Builder preference(String preference) {
            query.setPreference(preference);
            return this;
        }

        public Builder fields(List<String> fields) {
            query.setFields(fields);
            return this;
        }
        
        public Builder queryBuilder(QueryBuilder queryBuilder) {
            query.setQueryBuilder(queryBuilder);
            return this;
        }

        public Builder aggregationBuilders(List<AbstractAggregationBuilder> aggregationBuilders) {
            query.setAggregationBuilders(aggregationBuilders);
            return this;
        }
        
        public Builder sorts(List<SortBuilder> sorts) {
            query.setSorts(sorts);
            return this;
        }
        
        public Builder collapseBuilder(CollapseBuilder collapseBuilder) {
            query.setCollapseBuilder(collapseBuilder);
            return this;
        }
        
        public Builder suggestBuilder(SuggestBuilder suggestBuilder) {
            query.setSuggestBuilder(suggestBuilder);
            return this;
        }
        
        public EsQuery build() {
            return query;
        }
    }
    
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public boolean isLogEnable() {
        return logEnable;
    }

    public void setLogEnable(boolean logEnable) {
        this.logEnable = logEnable;
    }

    public boolean isQueryScore() {
        return queryScore;
    }

    public void setQueryScore(boolean queryScore) {
        this.queryScore = queryScore;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public List<AbstractAggregationBuilder> getAggregationBuilders() {
        return aggregationBuilders;
    }

    public void setAggregationBuilders(List<AbstractAggregationBuilder> aggregationBuilders) {
        this.aggregationBuilders = aggregationBuilders;
    }

    public List<SortBuilder> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortBuilder> sorts) {
        this.sorts = sorts;
    }

    public CollapseBuilder getCollapseBuilder() {
        return collapseBuilder;
    }

    public void setCollapseBuilder(CollapseBuilder collapseBuilder) {
        this.collapseBuilder = collapseBuilder;
    }

    public SuggestBuilder getSuggestBuilder() {
        return suggestBuilder;
    }

    public void setSuggestBuilder(SuggestBuilder suggestBuilder) {
        this.suggestBuilder = suggestBuilder;
    }
}
