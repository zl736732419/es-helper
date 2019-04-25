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
    SEARCH_ERROR(500, "search error"),
    PARAMS_EMPTY(1000, "params is empty"),
    TASK_EMPTY(1001, "task is empty"),
    UNKNOWN_FIELD_TYPE(1002, "field type unknown"),
    QUERY_BUILDER_EMPTY(1003, "query builder is empty"),
    ES_CONFIG_NULL(1004, "elasticsearch config empty"),
    CONFIG_PARSE_ERROR(1005, "config parse error"),
    CLUSTER_NULL(1006, "cluster is null"),
    ES_CLIENT_ERROR(1007, "es client error"),
    INDEX_NOT_EXISTS(1008, "index not exist"),
    SEARCH_QUERY_ERROR(1009, "es query error"),
    
    VALID_DOMAIN_NULL(1100, "domain is null"),
    VALID_DOMAIN_NOT_EXIST(1101, "domain is not exist"),
    VALID_ACCESS_TOKEN_NULL(1102, "access token is null"),
    VALID_ACCESS_TOKEN_INVALID(1103, "access token is not match with index"),
    VALID_TYPE_NOT_EXIST(1104, "type is not exist"),
    VALID_RECORD_OVERFLOW(1105, "number of record overflow"),
    VALID_PAGE_SIZE_INVALID(1106, "pageSize is invalid"),
    VALID_AGENT_NOT_EXIST(1107, "agent is invalid"),
    
    VALID_FILTER_FIELD_NULL(1201, "filter field is null"),
    VALID_FILTER_FIELD_NOT_EXIST(1202, "filter field is not exist"),
    VALID_FILTER_NOT_NULL(1203, "filter is null"),
    VALID_FILTER_FIELD_NOT_SUPPORT_QUERY(1204, "filter field not support query"),
    VALID_FILTER_COMMON_FIELD_VALUES_NULL(1205, "common filter field values must not be null"),
    VALID_FILTER_FIELD_INDEX_TYPE_NULL(1206, "index field type config not null"),
    VALID_FILTER_FIELD_INDEX_TYPE_UNSUPPORTED(1207, "index field config type unsupported"),
    VALID_FILTER_FIELD_VALUE_NULL(1208, "field value must not be null"),
    VALID_FILTER_FIELD_INT_TYPE(1209, "field value must be int value"),
    VALID_FILTER_FIELD_LONG_TYPE(1210, "field value must be long value"),
    VALID_FILTER_FIELD_DOUBLE_TYPE(1211, "field value must be double value"),
    
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
