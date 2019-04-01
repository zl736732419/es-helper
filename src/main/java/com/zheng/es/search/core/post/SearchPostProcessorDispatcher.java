package com.zheng.es.search.core.post;

import com.zheng.es.exceptions.SearchException;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询后处理
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class SearchPostProcessorDispatcher {
    @Autowired
    private ISearchPostProcessor highlightSearchPostProcessor;
    
    public void dispatch(Params params, Response response) throws SearchException {
        if (null == response || !response.isSuccess() || StringUtil.isEmpty(response.getData())) {
            return;
        }
        List<Map<String, Object>> items = response.getData();
        items.stream()
                .filter(item -> StringUtil.isNotEmpty(item))
                .forEach(item -> highlightSearchPostProcessor.process(params, item));
    }
}
