package com.zheng.es.ha;

import com.zheng.es.utils.ClusterPool;
import com.zheng.es.utils.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  随机选择
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月11日			zhenglian			    Initial.
 *
 * </pre>
 */
public class RandomLoadBalance implements ILoadBalance {

    private ClusterPool clusterPool = ClusterPool.getInstance();
    /**
     * 记录index所在集群总数
     */
    private Map<String, Integer> totalMap = new ConcurrentHashMap<>();

    public RandomLoadBalance() {
        init();
    }
    
    private void init() {
        Map<String, List<String>> indexClusters = clusterPool.getIndexClusters();
        indexClusters.forEach((key, clusters) -> totalMap.put(key, clusters.size()));
    }
    
    @Override
    public String select(String key, List<String> clusterKeys) {
        throw new UnsupportedOperationException("顺序轮流算法不支持该操作");
    }

    @Override
    public String select(String index) {
        Integer total = totalMap.get(index);
        if (StringUtil.isEmpty(total)) {
            return null;
        }
        int i = new Random().nextInt(total);
        List<String> clusterKeys = clusterPool.getIndexClusters().get(index);
        String clusterKey = clusterKeys.get(i);
        return clusterKey;
    }
}
