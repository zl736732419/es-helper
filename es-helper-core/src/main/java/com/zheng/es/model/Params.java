package com.zheng.es.model;

import com.zheng.es.field.FilterField;
import com.zheng.es.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
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
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月18日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Params {
    /**
     * 索引名
     */
    private String domain;

    /**
     * 查询的type
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
     * 平台
     */
    private String agent;
    /**
     * 查询条件
     */
    private List<FilterField> filters;
    /**
     * 原始json字符串
     */
    private String originalJson;
    /**
     * 滚动查询id
     */
    private String scrollId;
    
    private Map<String, Object> options = new HashMap<>();
    
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        if (StringUtil.isEmpty(type)) {
            type = "sku";
        }
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

    public List<FilterField> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterField> filters) {
        this.filters = filters;
    }

    public String getOriginalJson() {
        return originalJson;
    }

    public void setOriginalJson(String originalJson) {
        this.originalJson = originalJson;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public void addFilter(FilterField filterField) {
        if (null == filterField) {
            return;
        }
        if (null == filters) {
            filters = new ArrayList<>();
        }
        filters.add(filterField);
    }
    
    @Override
    public String toString() {
        return "Params{" +
                "domain='" + domain + '\'' +
                "type='" + type + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", agent=" + agent +
                ", filters=" + filters +
                ", originalJson='" + originalJson + '\'' +
                '}';
    }
}
