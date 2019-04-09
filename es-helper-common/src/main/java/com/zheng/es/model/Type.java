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
     * 字段列表
     */
    private Filters filters;

    /**
     * index所有字段集合
     */
    private Map<String, Field> _allFieldMap = new HashMap<>();
    
    /**
     * index平台召回字段集合
     */
    private Map<String, List<String>> _agentResponseFieldMap = new HashMap<>();
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
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
    
    public void addFieldToMap(Field field) {
        if (null == field) {
            return;
        }
        String fieldName = field.getName();
        _allFieldMap.put(fieldName, field);
        String hit = field.getHit();
        if (null == hit || Objects.equals(hit, "")) {
            return;
        }
        String[] agents = hit.split(",");
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
