package com.zheng.es.service;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.ha.ConsistantHashLoadBalance;
import com.zheng.es.ha.RoundRobinLoadBalance;
import com.zheng.es.utils.ClusterPool;
import com.zheng.es.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
@Service
public class ClusterServiceImpl implements IClusterService {
    private ClusterPool clusterPool = ClusterPool.getInstance();
    
    @Override
    public String getClusterKey(String key, String index) {
        List<String> clusterKeys = clusterPool.getIndexClusters(index);
        if (StringUtil.isEmpty(clusterKeys)) {
            throw new EsSearchException(EnumExceptionCode.CLUSTER_NULL.getKey(), "index[" + index + "] not config in es.");
        }
        if (clusterKeys.size() == 1) {
            return clusterKeys.get(0);
        }
        if (StringUtil.isEmpty(key)) {
            // 顺序轮询
            return new RoundRobinLoadBalance().select(index);
        }
        // 一致性hash
        return new ConsistantHashLoadBalance().select(key, clusterKeys);
    }
}
