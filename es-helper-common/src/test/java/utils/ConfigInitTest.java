package utils;

import com.zheng.es.model.Index;
import com.zheng.es.utils.IndexConfigUtil;
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
 *  2019年03月19日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ConfigInitTest {
    
    @Test
    public void test() {
        IndexConfigUtil config = IndexConfigUtil.getInstance();
        Index index = config.getIndex("sku");
        System.out.println(index);
    }
    
}
