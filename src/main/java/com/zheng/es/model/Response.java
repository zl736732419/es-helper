package com.zheng.es.model;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Response {
    /**
     * 索引名
     */
    private String index;
    /**
     * 分页页码
     */
    private Integer pageNo;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 召回记录总数
     */
    private Integer total;
    /**
     * 召回记录列表
     */
    private List<Map<String, Object>> data;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
    
    public static class Builder {
        private Response response;
        public Builder() {
            response = new Response();
        }
        
        public Builder pageNo(Integer pageNo) {
            response.setPageNo(pageNo);
            return this;
        }

        public Builder pageSize(Integer pageSize) {
            response.setPageSize(pageSize);
            return this;
        }

        public Builder total(Integer total) {
            response.setTotal(total);
            return this;
        }

        public Builder index(String index) {
            response.setIndex(index);
            return this;
        }

        public Builder data(List<Map<String, Object>> data) {
            response.setData(data);
            return this;
        }

        public Response build() {
            return response;
        }
    }
}
