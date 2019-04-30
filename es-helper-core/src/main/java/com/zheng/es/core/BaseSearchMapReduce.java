package com.zheng.es.core;

import com.zheng.es.enums.EnumSearchTask;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.task.AbstractSearchTask;
import com.zheng.es.task.SkuSearchTask;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
 *  基础查询任务
 *  将查询和聚合单独拆分任务查询
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
        if (StringUtils.isEmpty(params)) {
            return new ArrayList<>();
        }
        List<AbstractSearchTask> tasks = new ArrayList<>();
        // 添加列表查询任务
        SkuSearchTask skuTask = new SkuSearchTask(params, EnumSearchTask.SKU.getKey());
        tasks.add(skuTask);
        // TODO 聚合查询任务

        return tasks;
    }

    @Override
    public Response reduce(List<Response> responses) {
        // TODO
        return responses.get(0);
    }
}
