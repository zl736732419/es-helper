package com.zheng.es.config.util;

import com.zheng.es.config.model.Field;
import com.zheng.es.config.model.Fields;
import com.zheng.es.config.model.Filters;
import com.zheng.es.config.model.Index;
import com.zheng.es.utils.StringUtil;
import org.apache.commons.digester3.Digester;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
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
 *  加载索引配置
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ConfigInit {
    private Logger logger = LogManager.getLogger(this.getClass());
    Map<String, Index> indexMap = new HashMap<>();
    
    private ConfigInit() {
        initXml();
    }

    private void initXml() {
        configure("mapping");
    }

    private void configure(String mapping) {
        String parentPath = this.getClass().getClassLoader().getResource("mapping").getPath();
        File parent = FileUtils.getFile(parentPath);
        String[] extensions = new String[] {"xml"};
        Collection<File> xmls = FileUtils.listFiles(parent, extensions, false);
        if (StringUtils.isEmpty(xmls)) {
            logger.error("without no index config!");
            return;
        }
        xmls.forEach(xml -> parseXml(xml));
    }

    private void parseXml(File xml) {
        logger.info("start parsing xml config {}", xml.getName());
        Digester digester = new Digester();
        digester.setValidating(false);
        
        digester.addObjectCreate("index", Index.class);
        digester.addSetProperties("index");
        digester.addObjectCreate("index/filters", Filters.class);
        digester.addObjectCreate("index/filters/field", Field.class);
        digester.addSetProperties("index/filters/field");
        digester.addObjectCreate("index/filters/fields", Fields.class);
        digester.addSetProperties("index/filters/fields");
        digester.addObjectCreate("index/filters/fields/field", Field.class);
        digester.addSetProperties("index/filters/fields/field");
        
        digester.addSetNext("index/filters/field", "addField");
        digester.addSetNext("index/filters/fields", "addFields");
        digester.addSetNext("index/filters/fields/field", "addField");
        digester.addSetNext("index/filters", "setFilters");
        
        Index index = null;
        try {
            index = digester.parse(new FileInputStream(xml));
        } catch (Exception e) {
            logger.error("parse index config xml {} error.", xml.getName(), e);
        }
        if (null == index) {
            return;
        }
        prepareIndex(index);
        indexMap.put(index.getName(), index);
    }

    private void prepareIndex(Index index) {
        Filters filters = index.getFilters();
        if (StringUtils.isEmpty(filters)) {
            return;
        }
        parseFieldToMap(filters.getFieldList(), index);
        parseFieldsToMap(filters.getFieldsList(), index);
    }

    private void parseFieldsToMap(List<Fields> fieldsList, Index index) {
        if (StringUtils.isEmpty(fieldsList)) {
            return;
        }
        fieldsList.stream()
                .filter(fields -> StringUtil.isNotEmpty(fields))
                .forEach(fields -> index.addFieldsToMap(fields));
    }

    private void parseFieldToMap(List<Field> fields, Index index) {
        if (StringUtils.isEmpty(fields)) {
            return;
        }
        fields.stream()
                .filter(field -> StringUtil.isNotEmpty(field))
                .forEach(field -> index.addFieldToMap(field));
    }

    public Index getIndex(String index) {
        return indexMap.get(index);
    }
    
    private static class Inner {
        private static ConfigInit instance = new ConfigInit();
    }
    
    public static ConfigInit getInstance() {
        return Inner.instance;
    }
}
