package utils;

import com.zheng.es.model.Params;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        String json = readJsonFromResource();
        Params params = ParamsUtil.transform(json);
        System.out.println(params);
    }

    private String readJsonFromResource() throws Exception {
        URL url = ParamsUtilTest.class.getClassLoader().getResource("json/params.json");
        File file = new File(url.toURI());
        String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return json;
    }
}
