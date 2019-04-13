package com.zheng.es.model;

import com.zheng.es.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 *  2019年04月02日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Type {
    /**
     * type名称
     */
    private String name;

    /**
     * 简单字段列表
     */
    private List<Field> fieldList = new ArrayList<>();

    /**
     * nested字段列表
     */
    private List<Fields> fieldsList = new ArrayList<>();
    /**
     * index所有字段集合
     */
    private Map<String, Field> _allFieldMap = new HashMap<>();
    
    /**
     * index平台召回字段集合
     */
    private Map<String, List<String>> _agentResponseFieldMap = new HashMap<>();

    /**
     * 查询类型字段
     */
    private Map<String, List<String>> _queryTypeFieldMap = new HashMap<>(); 
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Map<String, List<String>> get_agentResponseFieldMap() {
        return _agentResponseFieldMap;
    }

    public void set_agentResponseFieldMap(Map<String, List<String>> _agentResponseFieldMap) {
        this._agentResponseFieldMap = _agentResponseFieldMap;
    }

    public Map<String, Field> get_allFieldMap() {
        return _allFieldMap;
    }

    public void set_allFieldMap(Map<String, Field> _allFieldMap) {
        this._allFieldMap = _allFieldMap;
    }

    public List<String> getAgentFields(String agent) {
        if (StringUtil.isEmpty(agent)) {
            return null;
        }
        return _agentResponseFieldMap.get(agent);
    }
    
    public Field getField(String fieldName) {
        return _allFieldMap.get(fieldName);
    }

    public Map<String, List<String>> get_queryTypeFieldMap() {
        return _queryTypeFieldMap;
    }

    public void set_queryTypeFieldMap(Map<String, List<String>> _queryTypeFieldMap) {
        this._queryTypeFieldMap = _queryTypeFieldMap;
    }
    
    public List<String> getQueryTypeFields(String queryType) {
        if (StringUtil.isEmpty(queryType)) {
            return null;
        }
        return _queryTypeFieldMap.get(queryType);
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
    
    public void addFieldToMap(Field field) {
        if (null == field) {
            return;
        }
        addAllField(field);
        addAgentField(field);
        addQueryTypeField(field);
    }

    private void addQueryTypeField(Field field) {
        String queryType = field.getQueryType();
        if (StringUtil.isEmpty(queryType)) {
            return;
        }
        String[] arr = queryType.split(",");
        Arrays.stream(arr)
                .filter(qt -> StringUtil.isNotEmpty(qt))
                .forEach(qt -> {
                    List<String> fields = _queryTypeFieldMap.get(qt);
                    if (StringUtil.isEmpty(fields)) {
                        fields = new ArrayList<>();
                        _queryTypeFieldMap.put(qt, fields);
                    }
                    fields.add(field.getName());
                });
    }

    private void addAllField(Field field) {
        String fieldName = field.getName();
        _allFieldMap.put(fieldName, field);
    }

    private void addAgentField(Field field) {
        String hit = field.getHit();
        if (null == hit || Objects.equals(hit, "")) {
            return;
        }
        String[] agents = hit.split(",");
        String fieldName = field.getName();
        Arrays.stream(agents)
                .filter(agent -> StringUtil.isNotEmpty(agent))
                .forEach(agent -> {
                    List<String> agentFields = _agentResponseFieldMap.get(agent);
                    if (null == agentFields) {
                        agentFields = new ArrayList<>();
                        _agentResponseFieldMap.put(agent, agentFields);
                    }
                    if (!agentFields.contains(fieldName)) {
                        agentFields.add(fieldName);
                    }
                });
    }

    public void addFieldsToMap(Fields fields) {
        if (null == fields) {
            return;
        }
        List<Field> fieldList = fields.getFields();
        if (StringUtil.isEmpty(fieldList)) {
            return;
        }
        String path = fields.getName();
        if (StringUtil.isEmpty(path)) {
            return;
        }
        fieldList.stream()
                .filter(field -> StringUtil.isNotEmpty(field))
                .forEach(field -> {
                    String fieldName = new StringBuilder(path)
                            .append(".").append(field.getName()).toString();
                    field.setName(fieldName);
                    field.setNestedPath(fields.getName());
                    field.setFieldsType(fields.getType());
                    addFieldToMap(field);
                });
    }
}
