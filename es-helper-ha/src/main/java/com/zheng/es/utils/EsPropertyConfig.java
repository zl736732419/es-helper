package com.zheng.es.utils;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

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
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsPropertyConfig {
    
    private Map<String, String> configs = new LinkedHashMap<>();
    
    private EsPropertyConfig() {
        initProps();
    }

    private void initProps() {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("elasticsearch.properties");
        Properties props = new Properties();
        try {
            props.load(input);
        } catch (IOException e) {
            throw new EsSearchException(EnumExceptionCode.ES_CONFIG_NULL);
        }finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        for(String key : props.stringPropertyNames()) {
            configs.put(key, props.getProperty(key));
        }
    }

    public Map<String, String> getConfigs() {
        return configs;
    }

    public String getProperty(String key) {
        return configs.get(key);
    }
    
    private static class Inner {
        private static final EsPropertyConfig instance = new EsPropertyConfig();
    }
    
    public static EsPropertyConfig getInstance() {
        return Inner.instance;
    }
    
}
