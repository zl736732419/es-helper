package com.zheng.es.service;

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
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface IClusterService {
    /**
     * 获取索引对应的集群连接
     * @param key
     * @param index
     * @return
     */
    String getClusterKey(String key, String index);
}
