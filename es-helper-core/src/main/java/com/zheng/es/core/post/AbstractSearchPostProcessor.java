package com.zheng.es.core.post;

import com.zheng.es.exceptions.SearchException;
import com.zheng.es.model.Params;
import com.zheng.es.utils.StringUtil;

import java.util.Map;

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
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public abstract class AbstractSearchPostProcessor implements ISearchPostProcessor {
    @Override
    public ISearchPostProcessor next() {
        return null;
    }

    @Override
    public void process(Params params, Map<String, Object> data) throws SearchException {
        if (StringUtil.isEmpty(data)) {
            return;
        }
        doProcess(params, data);
        ISearchPostProcessor nextProcessor = next();
        if (null != nextProcessor) {
            nextProcessor.process(params, data);
        }
    }
    
    public abstract void doProcess(Params params, Map<String, Object> data);
}
