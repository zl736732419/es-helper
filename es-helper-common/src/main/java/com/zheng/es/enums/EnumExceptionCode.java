package com.zheng.es.enums;

import com.zheng.es.utils.StringUtil;

import java.util.Objects;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询异常码枚举
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public enum EnumExceptionCode {
    SUCCESS(200, "search ok"),
    PARAMS_EMPTY(1000, "params is empty"),
    TASK_EMPTY(1001, "task is empty"),
    UNKNOWN_FIELD_TYPE(1002, "field type unknown"),
    QUERY_BUILDER_EMPTY(1003, "query builder is empty"),
    ES_CONFIG_NULL(1004, "elasticsearch config empty"),
    CONFIG_PARSE_ERROR(1005, "config parse error"),
    CLUSTER_NULL(1006, "cluster is null"),
    ES_CLIENT_ERROR(1007, "es client error"),
    UNKNOWN_ERROR(9999, "unknown error")
    //TODO
    ;
    private Integer key;
    private String value;

    EnumExceptionCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public static EnumExceptionCode findByKey(Integer key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (EnumExceptionCode exceptionCode : EnumExceptionCode.values()) {
            if (Objects.equals(exceptionCode.getKey(), key)) {
                return exceptionCode;
            }
        }
        return null;
    }
}
