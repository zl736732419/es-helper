package com.zheng.es.model;

import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;

import java.util.Objects;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  es响应结果
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月12日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsSearchResponse {
    /**
     * 请求状态
     */
    private RestStatus status;
    /**
     * 请求耗费时间
     */
    private TimeValue took;

    /**
     * 是否提前中断
     */
    private boolean terminatedEarly;

    /**
     * 是否超时
     */
    private boolean timeout;
    
    /**
     * 查询结果
     */
    private SearchHits searchHits;
    /**
     * 聚合结果
     */
    private Aggregations aggregations;
    /**
     * 滚动查询id
     */
    private String scrollId;
    
    private EsSearchResponse() {}
    
    public RestStatus getStatus() {
        return status;
    }

    public void setStatus(RestStatus status) {
        this.status = status;
    }

    public TimeValue getTook() {
        return took;
    }

    public void setTook(TimeValue took) {
        this.took = took;
    }

    public boolean isTerminatedEarly() {
        return terminatedEarly;
    }

    public void setTerminatedEarly(boolean terminatedEarly) {
        this.terminatedEarly = terminatedEarly;
    }

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public SearchHits getSearchHits() {
        return searchHits;
    }

    public void setSearchHits(SearchHits searchHits) {
        this.searchHits = searchHits;
    }

    public Aggregations getAggregations() {
        return aggregations;
    }

    public void setAggregations(Aggregations aggregations) {
        this.aggregations = aggregations;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public boolean isSuccess() {
        return !isTimeout() && !isTerminatedEarly() && Objects.equals(status.getStatus(), RestStatus.OK.getStatus());
    }
    
    public static class Builder {
        private EsSearchResponse response;
        
        public Builder() {
            response = new EsSearchResponse();
        }

        public Builder status(RestStatus status) {
            response.status = status;
            return this;
        }

        public Builder took(TimeValue took) {
            response.took = took;
            return this;
        }

        public Builder terminatedEarly(boolean terminatedEarly) {
            response.terminatedEarly = terminatedEarly;
            return this;
        }

        public Builder timeout(boolean timeout) {
            response.timeout = timeout;
            return this;
        }
        
        public Builder searchHits(SearchHits searchHits) {
            response.searchHits = searchHits;
            return this;
        }
        
        public Builder aggregations(Aggregations aggregations) {
            response.aggregations = aggregations;
            return this;
        }
        
        public Builder scrollId(String scrollId) {
            response.scrollId = scrollId;
            return this;
        }
        
        public EsSearchResponse build() {
            return response;
        }
    }
}
