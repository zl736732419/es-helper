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
 *  2019年03月18日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class FilterField extends BaseField {
    private String pair;
    private String operator;

    public FilterField(String field, EnumFieldType fieldType) {
        super(field, fieldType);
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
