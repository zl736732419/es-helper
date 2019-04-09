package com.zheng.es.ha;

import com.zheng.es.utils.ClusterPool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  顺序轮流策略
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class RoundRobinLoadBalance extends AbstractLoadBalance {
    
    private ClusterPool clusterPool = ClusterPool.getInstance();
    /**
     * 记录index所在集群总数
     */
    private Map<String, Integer> totalMap = new ConcurrentHashMap<>();
    /**
     * 记录当前index遍历次数
     */
    private Map<String, AtomicInteger> currentMap = new ConcurrentHashMap<>();
    
    public RoundRobinLoadBalance() {
        init();
    }

    private void init() {
        Map<String, List<String>> indexClusters = clusterPool.getIndexClusters();
        indexClusters.forEach((key, clusters) -> {
            totalMap.put(key, clusters.size());
            currentMap.put(key, new AtomicInteger(clusters.size() - 1));
        });
    }

    @Override
    public String select(String key, List<String> clusterKeys) {
        throw new UnsupportedOperationException("顺序轮流算法不支持该操作");
    }

    @Override
    public String select(String index) {
        AtomicInteger current = currentMap.get(index);
        if (null == current) {
            return null;
        }
        Integer total = totalMap.get(index);
        int i = current.incrementAndGet() % total;
        List<String> clusterKeys = clusterPool.getIndexClusters().get(index);
        String clusterKey = clusterKeys.get(i);
        currentMap.put(index, new AtomicInteger(i));
        return clusterKey;
    }
}
