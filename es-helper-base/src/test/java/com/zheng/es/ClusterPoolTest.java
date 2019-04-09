package com.zheng.es;

import com.zheng.es.utils.ClusterPool;
import com.zheng.es.model.Cluster;
import org.junit.Test;

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
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ClusterPoolTest {
    @Test
    public void test() {
        Map<String, Cluster> clusters = ClusterPool.getInstance().getClusters();
        System.out.println(clusters);
        Map<String, List<String>> indexClusters = ClusterPool.getInstance().getIndexClusters();
        System.out.println(indexClusters);
    }
}
