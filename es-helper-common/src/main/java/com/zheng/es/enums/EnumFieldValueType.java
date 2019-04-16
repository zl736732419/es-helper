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
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public enum EnumFieldValueType {
    /**
     * 等值过滤
     */
    STRING("string"),
    INT("int"),
    DOUBLE("double"),
    LONG("long")
    
    ;

    private String key;

    EnumFieldValueType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static EnumFieldValueType findByValue(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (EnumFieldValueType type : EnumFieldValueType.values()) {
            if (Objects.equals(type.getKey(), key)) {
                return type;
            }
        }
        return null;
    }
    
}
