package com.zheng.es.ha;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  一致性hash策略
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class ConsistantHashLoadBalance extends AbstractLoadBalance {

    @Override
    public String select(String index) {
        throw new UnsupportedOperationException("一致性hash算法不支持该操作");
    }

    @Override
    public String select(String key, List<String> clusterKeys) {
        return super.select(key, clusterKeys);
    }
}
