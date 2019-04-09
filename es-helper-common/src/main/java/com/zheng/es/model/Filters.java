package com.zheng.es.model;

import java.util.ArrayList;
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
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Filters {
    private List<Field> fieldList;
    private List<Fields> fieldsList;

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public List<Fields> getFieldsList() {
        return fieldsList;
    }

    public void setFieldsList(List<Fields> fieldsList) {
        this.fieldsList = fieldsList;
    }
    
    public void addField(Field field) {
        if (null == field) {
            return;
        }
        if (null == fieldList) {
            fieldList = new ArrayList<>();
        }
        fieldList.add(field);
    }

    public void addFields(Fields fields) {
        if (null == fields) {
            return;
        }
        if (null == fieldsList) {
            fieldsList = new ArrayList<>();
        }
        fieldsList.add(fields);
    }
}
