package com.zheng.es.model.log;

import com.zheng.es.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  日志上下文
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月30日			zhenglian			    Initial.
 *
 * </pre>
 */
public class LogContext {
    private boolean logEnable;
    private List<String> logs = new ArrayList<>();

    public boolean isLogEnable() {
        return logEnable;
    }

    public void setLogEnable(boolean logEnable) {
        this.logEnable = logEnable;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }
    
    public void appendLog(String line) {
        if (StringUtil.isEmpty(line)) {
            return;
        }
        logs.add(line);
    }
}
