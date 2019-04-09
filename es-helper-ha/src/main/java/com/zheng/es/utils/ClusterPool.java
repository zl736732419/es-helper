package com.zheng.es.utils;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.zheng.es.model.Cluster;
import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;

import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ClusterPool {
    private EsPropertyConfig config = EsPropertyConfig.getInstance();
    private static final String CLUSTER_PREFIX = "es.cluster.";
    private static final String URL_PREFIX = "{0}.url";
    private static final String INDEX_PREFIX = "{0}.index";
    /**
     * 集群信息
     */
    private Map<String, Cluster> clusters = new HashMap<>();
    /**
     * 索引对应的集群列表
     */
    private Map<String, List<String>> indexClusters = new HashMap<>();
    
    private ClusterPool() {
        initClusters();
    }

    private void initClusters() {
        Map<String, String> clusterNames = Maps.filterEntries(config.getConfigs(), new Predicate<Map.Entry<String, String>>() {
            @Override
            public boolean apply(@Nullable Map.Entry<String, String> input) {
                String key = input.getKey();
                return key.length() == CLUSTER_PREFIX.length() + 1;
            }
        });
        clusterNames.forEach((key, value) -> {
            Cluster.Builder builder = new Cluster.Builder();
            if (StringUtil.isEmpty(value)) {
                throw new EsSearchException(EnumExceptionCode.CONFIG_PARSE_ERROR.getKey(), key + " is empty");
            }
            builder.name(value);
            String urlKey = MessageFormat.format(URL_PREFIX, key);
            String url = config.getProperty(urlKey);
            if (StringUtil.isEmpty(url)) {
                throw new EsSearchException(EnumExceptionCode.CONFIG_PARSE_ERROR.getKey(), urlKey + " is empty");
            }
            builder.nodes(url);
            String indexKey = MessageFormat.format(INDEX_PREFIX, key);
            String indexStr = config.getProperty(indexKey);
            if (StringUtil.isEmpty(indexStr)) {
                throw new EsSearchException(EnumExceptionCode.CONFIG_PARSE_ERROR.getKey(), indexKey + " is empty");
            }
            builder.indexes(indexStr);
            clusters.put(key, builder.build());
            parseIndexCluster(key, indexStr);
        });
    }

    private void parseIndexCluster(String key, String indexStr) {
        String[] indexs = indexStr.split(",");
        Arrays.stream(indexs)
                .filter(index -> StringUtil.isNotEmpty(index))
                .forEach(index -> {
                    List<String> clusters = indexClusters.get(index);
                    if (null == clusters) {
                        clusters = new ArrayList<>();
                        indexClusters.put(index, clusters);
                    }
                    clusters.add(key);
                });
    }

    public Map<String, Cluster> getClusters() {
        return clusters;
    }

    public Map<String, List<String>> getIndexClusters() {
        return indexClusters;
    }

    public List<String> getIndexClusters(String index) {
        return indexClusters.get(index);
    }
    
    private static class Inner {
        private static ClusterPool instance = new ClusterPool();
    }
    
    public static ClusterPool getInstance() {
        return Inner.instance;
    }
}
