package com.zheng.es.search.core.post;

import com.zheng.es.exceptions.SearchException;
import com.zheng.es.model.Params;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  高亮处理
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class HighlightSearchPostProcessor extends AbstractSearchPostProcessor {

    @Override
    public void doProcess(Params params, Map<String, Object> data) throws SearchException {
        // TODO 处理高亮
    }
}
