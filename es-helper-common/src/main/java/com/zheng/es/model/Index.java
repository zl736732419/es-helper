package com.zheng.es.model;

import com.zheng.es.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Index {
    /**
     * 索引名
     */
    private String domain;
    /**
     * 索引支持的平台列表
     */
    private String agents;
    /**
     * 去同款
     */
    private String cardinality;
    /**
     * id查询所用字段
     */
    private String idField;
    /**
     * id查询匹配的关键字格式
     */
    private String idPattern;
    /**
     * 索引类型列表
     */
    private List<Type> types = new ArrayList<>();

    private Map<String, Type> _typeMap = new HashMap<>();

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public List<Type> getTypes() {
        return types;
    }
    
    public Type getType(String typeName) {
        if (StringUtil.isEmpty(_typeMap)) {
            return null;
        }
        return _typeMap.get(typeName);
    }
    
    public void putTypeToMap(Type type) {
        if (null == type) {
            return;
        }
        _typeMap.put(type.getName(), type);
    }
    
    public Map<String, Type> get_typeMap() {
        return _typeMap;
    }

    public void set_typeMap(Map<String, Type> _typeMap) {
        this._typeMap = _typeMap;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public String getAgents() {
        return agents;
    }

    public void setAgents(String agents) {
        this.agents = agents;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getIdPattern() {
        return idPattern;
    }

    public void setIdPattern(String idPattern) {
        this.idPattern = idPattern;
    }
    
    public void addType(Type type) {
        if (StringUtil.isEmpty(type)) {
            return;
        }
        types.add(type);
    }
}
