package com.zheng.es.field;

import com.zheng.es.enums.EnumFieldType;

import java.util.List;

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
 *  2019年03月18日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class FilterField extends BaseField {
    private List<Object> values;
    private String pair;
    private String operator;

    public FilterField(String field, EnumFieldType fieldType) {
        super(field, fieldType);
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }
}
