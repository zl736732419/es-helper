package com.zheng.es.model.log;

import com.zheng.es.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  日志上下文，记录程序执行的重要日志
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月30日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsLoggerBuffer {
    
    private static ThreadLocal<LogContext> ctx = new ThreadLocal<>();
    
    public static void setLogEnable(boolean logEnable) {
        LogContext logContext = ctx.get();
        if (StringUtil.isEmpty(logContext)) {
            logContext = new LogContext();
            ctx.set(logContext);
        }
        logContext.setLogEnable(logEnable);
    }
    
    public static void clear() {
        LogContext logContext = ctx.get();
        if (StringUtil.isEmpty(logContext)) {
            return;
        }
        logContext.setLogEnable(false);
        logContext.setLogs(new ArrayList<>());
    }
    
    public static void appendIfLogEnable(String log) {
        LogContext logContext = ctx.get();
        if (StringUtil.isEmpty(logContext)) {
            return;
        }
        if (logContext.isLogEnable()) {
            logContext.appendLog(log);
        }
    }
    
    public static List<String> getLogs() {
        LogContext logContext = ctx.get();
        if (StringUtil.isEmpty(logContext)) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(logContext.getLogs());
    }
    
}
