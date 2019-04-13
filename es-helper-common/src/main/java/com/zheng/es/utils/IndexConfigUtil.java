package com.zheng.es.utils;

import com.zheng.es.model.Field;
import com.zheng.es.model.Fields;
import com.zheng.es.model.Index;
import com.zheng.es.model.Type;
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
public class IndexConfigUtil {
    private Logger logger = LogManager.getLogger(this.getClass());
    Map<String, Index> indexMap = new HashMap<>();
    
    private IndexConfigUtil() {
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
        digester.addObjectCreate("index/type", Type.class);
        digester.addSetProperties("index/type");
        digester.addObjectCreate("index/type/field", Field.class);
        digester.addSetProperties("index/type/field");
        digester.addObjectCreate("index/type/fields", Fields.class);
        digester.addSetProperties("index/type/fields");
        digester.addObjectCreate("index/type/fields/field", Field.class);
        digester.addSetProperties("index/type/fields/field");

        digester.addSetNext("index/type", "addType");
        digester.addSetNext("index/type/field", "addField");
        digester.addSetNext("index/type/fields", "addFields");
        digester.addSetNext("index/type/fields/field", "addField");
        
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
        indexMap.put(index.getDomain(), index);
    }

    private void prepareIndex(Index index) {
        if (null == index) {
            return;
        }
        for (Type type : index.getTypes()) {
            parseFieldToMap(type.getFieldList(), type);
            parseFieldsToMap(type.getFieldsList(), type);
            index.putTypeToMap(type);
        }
    }

    private void parseFieldsToMap(List<Fields> fieldsList, Type type) {
        if (StringUtils.isEmpty(fieldsList)) {
            return;
        }
        fieldsList.stream()
                .filter(fields -> StringUtil.isNotEmpty(fields))
                .forEach(fields -> type.addFieldsToMap(fields));
    }

    private void parseFieldToMap(List<Field> fields, Type type) {
        if (StringUtils.isEmpty(fields)) {
            return;
        }
        fields.stream()
                .filter(field -> StringUtil.isNotEmpty(field))
                .forEach(field -> type.addFieldToMap(field));
    }

    public Index getIndex(String index) {
        return indexMap.get(index);
    }
    
    private static class Inner {
        private static IndexConfigUtil instance = new IndexConfigUtil();
    }
    
    public static IndexConfigUtil getInstance() {
        return Inner.instance;
    }
}
