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
 *  查询任务
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public enum EnumSearchTask {
    SKU("sku", "列表查询"),
    AGG("agg", "聚合查询")
    ;
    private String key;
    private String value;

    EnumSearchTask(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public static EnumSearchTask findByKey(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (EnumSearchTask task : EnumSearchTask.values()) {
            if (Objects.equals(task.getKey(), key)) {
                return task;
            }
        }
        return null;
    }
}
