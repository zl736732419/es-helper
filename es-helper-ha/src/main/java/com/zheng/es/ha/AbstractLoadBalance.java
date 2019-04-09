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
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class AbstractLoadBalance implements ILoadBalance {
    @Override
    public String select(String key, List<String> clusterKeys) {
        return null;
    }

    @Override
    public String select(String index) {
        return null;
    }
}
