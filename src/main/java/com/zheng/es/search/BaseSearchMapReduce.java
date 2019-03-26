package com.zheng.es.search;

import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.search.tasks.AbstractSearchTask;
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
 *  基础查询任务：将查询和聚合单独划分任务查询
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class BaseSearchMapReduce implements ISearchMapReduce<Response> {
    @Override
    public List<AbstractSearchTask> map(Params params) {
        // TODO
        return null;
    }

    @Override
    public Response reduce(List<Response> responses) {
        // TODO
        return null;
    }
}
