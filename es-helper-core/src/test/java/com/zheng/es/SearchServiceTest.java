package com.zheng.es;

import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
import com.zheng.es.service.ISearchService;
import com.zheng.es.utils.ParamsUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  TODO
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月16日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchServiceTest extends BaseServiceTest {
    @Autowired
    private ISearchService searchService;
    
    @Test
    public void search() throws Exception {
        Params param = ParamsUtil.transform("gb.json");
        Response response = searchService.search(param);
        System.out.println(response);
    }
    
}
