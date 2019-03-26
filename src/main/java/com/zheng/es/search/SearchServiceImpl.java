package com.zheng.es.search;

import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询接口实现，具体起一个中转作用，具体查询逻辑将下发到某一个SearchTemplate执行
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
@Service
public class SearchServiceImpl implements ISearchService {
    @Autowired
    private ISearchAcceptor<Response> searchAcceptor;
    
    @Override
    public Response search(Params params) throws Exception {
        return searchAcceptor.accept(params);
    }
}
