package com.zheng.es.ha;

import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  负载均衡算法
 *  返回ES查询集群key
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface ILoadBalance {
    
    String select(String key, List<String> clusterKeys);
    
    String select(String index);
    
    
}
