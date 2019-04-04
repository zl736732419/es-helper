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
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月04日			zhenglian			    Initial.
 *
 * </pre>
 */
public enum EnumQueryOption {
    QUERY_LOG_ENABLE("query.log.enable", "日志开关"),
    QUERY_SCORE_ENABLE("query.score.enable", "显示得分开关")
    ;
    
    private String key;
    private String value;

    EnumQueryOption(String key, String value) {
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
    
    public static EnumQueryOption findByKey(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (EnumQueryOption option : EnumQueryOption.values()) {
            if (Objects.equals(option.getKey(), key)) {
                return option;
            }
        }
        return null;
    }
}
