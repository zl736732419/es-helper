package com.zheng.es.utils;

import com.zheng.es.field.FilterField;
import com.zheng.es.model.Params;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SignUtil {
    private static Logger logger = LogManager.getLogger(SignUtil.class);

    /**
     * 唯一键，考虑到分页
     *
     * @param params
     * @return
     */
    public static String uniqueKeyWithPage(Params params) {
        StringBuilder builder = build(params);
        if (StringUtil.isEmpty(builder)) {
            return null;
        }
        builder.append(params.getPageNo())
                .append(params.getPageSize());
        return md5(builder.toString());
    }

    /**
     * 唯一键，不考虑分页
     *
     * @param params
     * @return
     */
    public static String uniqueKeyWithoutPage(Params params) {
        StringBuilder builder = build(params);
        if (StringUtil.isEmpty(builder)) {
            return null;
        }
        return md5(builder.toString());
    }

    private static StringBuilder build(Params params) {
        if (StringUtil.isEmpty(params)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(params.getDomain())
                .append(params.getType())
                .append(params.getAgent());
        if (StringUtil.isNotEmpty(params.getFilters())) {
            for (FilterField filter : params.getFilters()) {
                builder.append(filter.toString());
            }
        }
        return builder;
    }

    private static String md5(String source) {
        try {
            return Md5Util.md5(source);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
