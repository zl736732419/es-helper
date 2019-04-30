package com.zheng.es.task;

import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.model.log.EsLoggerBuffer;
import com.zheng.es.utils.StringUtil;
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
 *  sku列表查询任务
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class AbstractSearchTask implements ISearchTask<Response> {
    private Logger logger = LogManager.getLogger(this.getClass());
    protected Params params;
    protected String taskName;
    
    public AbstractSearchTask(Params params, String taskName) {
        this.params = params;
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public Response call() throws Exception {
        Response response = null;
        try {
            EsLoggerBuffer.setLogEnable(params.isQueryLogEnable());
            response = execute();
        } catch (Exception e) {
            logger.error(e);
        } finally {
            appendLog(response);
        }
        return response;
    }

    private void appendLog(Response response) {
        if (StringUtil.isEmpty(response)) {
            return;
        }
        response.setLog(EsLoggerBuffer.getLogs());
        EsLoggerBuffer.clear();
    }
}
