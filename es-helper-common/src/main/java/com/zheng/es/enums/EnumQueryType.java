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
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public enum EnumQueryType {
    TERM("term")
    ;
    
    private String value;

    EnumQueryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public EnumQueryType findByKey(String key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        for (EnumQueryType type : EnumQueryType.values()) {
            if (Objects.equals(type.getValue(), key)) {
                return type;
            }
        }
        return null;
    }
}
