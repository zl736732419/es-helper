package com.zheng.es.model;

import com.zheng.es.field.FilterField;

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
 *  2019年04月04日			zhenglian			    Initial.
 *
 * </pre>
 */
public class QueryParams {
    /**
     * 索引名
     */
    private String domain;
    /**
     * 索引类型
     */
    private String type;
    /**
     * 分页页码
     */
    private Integer pageNo = 1;
    /**
     * 分页大小
     */
    private Integer pageSize = 36;
    /**
     * 查询偏好
     */
    private String preference;
    /**
     * 滚动查询id
     */
    private String scrollId;

    /**
     * 查询召回字段
     */
    private List<String> fields;

    /**
     * 查询过滤条件
     */
    private List<FilterField> filters;
    
    // TODO boost filter

    /**
     * 是否展示得分
     */
    private boolean queryScoreEnable;
    /**
     * 是否开启日志
     */
    private boolean logEnable;
    /**
     * unique key
     */
    private String key;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<FilterField> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterField> filters) {
        this.filters = filters;
    }

    public boolean isQueryScoreEnable() {
        return queryScoreEnable;
    }

    public void setQueryScoreEnable(boolean queryScoreEnable) {
        this.queryScoreEnable = queryScoreEnable;
    }

    public boolean isLogEnable() {
        return logEnable;
    }

    public void setLogEnable(boolean logEnable) {
        this.logEnable = logEnable;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public static class Builder {
        private QueryParams queryParams;
        
        public Builder() {
            queryParams = new QueryParams();
        }
        
        public Builder domain(String domain) {
            queryParams.domain = domain;
            return this;
        }
        
        public Builder type(String type) {
            queryParams.type = type;
            return this;
        }

        public Builder pageNo(Integer pageNo) {
            queryParams.pageNo = pageNo;
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            queryParams.pageSize = pageSize;
            return this;
        }

        public Builder preference(String preference) {
            queryParams.preference = preference;
            return this;
        }

        public Builder scrollId(String scrollId) {
            queryParams.scrollId = scrollId;
            return this;
        }
        
        public Builder fields(List<String> fields) {
            queryParams.fields = fields;
            return this;
        }
        
        public Builder filters(List<FilterField> filters) {
            queryParams.filters = filters;
            return this;
        }
        
        public Builder queryScoreEnable(boolean queryScoreEnable) {
            queryParams.queryScoreEnable = queryScoreEnable;
            return this;
        }
        
        public Builder logEnable(boolean logEnable) {
            queryParams.logEnable = logEnable;
            return this;
        }
        
        public Builder key(String key) {
            queryParams.key = key;
            return this;
        }
        
        public QueryParams build() {
            return queryParams;
        }
    }
}
