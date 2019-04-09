package com.zheng.es.field;

import com.zheng.es.enums.EnumFieldType;

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
public class BaseField {
    /**
     * 查询字段
     */
    private String field;
    /**
     * 字段类型
     */
    private EnumFieldType fieldType;

    public BaseField(String field, EnumFieldType fieldType) {
        this.field = field;
        this.fieldType = fieldType;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public EnumFieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(EnumFieldType fieldType) {
        this.fieldType = fieldType;
    }
}
