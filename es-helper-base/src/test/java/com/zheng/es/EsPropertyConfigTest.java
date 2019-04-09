package com.zheng.es;

import com.zheng.es.utils.EsPropertyConfig;
import org.junit.Test;

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
public class EsPropertyConfigTest {
    @Test
    public void test() {
        Map<String, String> configs = EsPropertyConfig.getInstance().getConfigs();
        System.out.println(configs);
    }
}
