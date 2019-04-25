package com.zheng.es.utils;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月16日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ExceptionUtil {
    public static void handleValidateException(EnumExceptionCode exceptionCode, String field) {
        Integer code = exceptionCode.getKey();
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotEmpty(field)) {
            builder.append("field:").append(field);
        }
        builder.append(" validate failed. ")
                .append("error msg: ").append(exceptionCode.getValue())
                .toString();
        throw new EsSearchException(code, builder.toString());
    }
}
