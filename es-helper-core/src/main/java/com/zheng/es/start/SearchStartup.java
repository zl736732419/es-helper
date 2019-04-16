package com.zheng.es.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  服务启动
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月13日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchStartup {
    private static Logger logger = LogManager.getLogger(SearchStartup.class);
    
    public static void main(String[] args) {
        ServerBootstrap.getInstance().start();
        logger.info("server start success...");
    }
}
