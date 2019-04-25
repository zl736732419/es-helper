package com.zheng.es;

import com.zheng.es.model.Params;
import com.zheng.es.utils.ParamsUtil;
import org.junit.Test;

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
 *  2019年03月18日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ParamsUtilTest {
    
    @Test
    public void test() throws Exception {
        Params params = ParamsUtil.transform("gb.json");
        System.out.println(params);
    }
}
