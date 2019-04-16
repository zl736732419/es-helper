package com.zheng.es.start;

import com.zheng.es.utils.StringUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  上下文管理器
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月16日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchApplicationContext {
    private String defaultCfg = "spring/spring-global.xml";
    private ClassPathXmlApplicationContext ctx;
    private SearchApplicationContext() {
    }
    
    private static class Inner {
        private static SearchApplicationContext ctx = new SearchApplicationContext();
    }
    
    public static SearchApplicationContext getInstance() {
        return Inner.ctx;
    }
    
    public void init(String... configs) {
        if (StringUtil.isEmpty(configs)) {
            configs = new String[] {defaultCfg};
        }
        if (null == ctx) {
            ctx = new ClassPathXmlApplicationContext(configs);
            ctx.start();
        }
    }
}
