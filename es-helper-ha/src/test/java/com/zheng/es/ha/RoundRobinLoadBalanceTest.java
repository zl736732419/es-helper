package com.zheng.es.ha;

import org.junit.Test;

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
public class RoundRobinLoadBalanceTest {
    @Test
    public void test() {
        String clusterKey = new RoundRobinLoadBalance().select("gb");
        System.out.println(clusterKey);
    }
}
