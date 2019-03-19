package com.zheng.es.config.model;

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
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Index {
    /**
     * 索引名
     */
    private String name;
    /**
     * 去同款
     */
    private String cardinality;
    /**
     * 字段列表
     */
    private Filters filters;

    /**
     * 索引支持的平台列表
     */
    private String agent;
    
    /**
     * index所有字段集合
     */
    private Map<String, Object> allFieldMap = new HashMap<>();
    /**
     * index平台召回字段集合
     */
    private Map<String, List<String>> agentResponseFieldMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Map<String, Object> getAllFieldMap() {
        return allFieldMap;
    }

    public void setAllFieldMap(Map<String, Object> allFieldMap) {
        this.allFieldMap = allFieldMap;
    }

    public Map<String, List<String>> getAgentResponseFieldMap() {
        return agentResponseFieldMap;
    }

    public void setAgentResponseFieldMap(Map<String, List<String>> agentResponseFieldMap) {
        this.agentResponseFieldMap = agentResponseFieldMap;
    }

    public void addFieldToMap(Field field) {
        if (null == field) {
            return;
        }
        String fieldName = field.getName();
        allFieldMap.put(fieldName, field);
        String hit = field.getHit();
        if (null == hit || Objects.equals(hit, "")) {
            return;
        }
        String[] agents = hit.split(",");
        Arrays.stream(agents)
                .filter(agent -> StringUtil.isNotEmpty(agent))
                .forEach(agent -> {
                    List<String> agentFields = agentResponseFieldMap.get(agent);
                    if (null == agentFields) {
                        agentFields = new ArrayList<>();
                        agentResponseFieldMap.put(agent, agentFields);
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
                    addFieldToMap(field);
                });
    }
}
