package com.zheng.es.utils;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Params;
import com.zheng.es.model.Response;
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
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ExceptionUtil {
    
    public static Response handleException(Logger logger, Params params, Exception e) {
        if (null == e) {
            return null;
        }
        logger.error("search error. params: {}", params, e);
        Integer code;
        String msg;
        if (e instanceof EsSearchException) {
            EsSearchException searchException = (EsSearchException) e;
            code = searchException.getCode();
            msg = searchException.getMessage();
        } else {
            code = EnumExceptionCode.UNKNOWN_ERROR.getKey();
            msg = EnumExceptionCode.UNKNOWN_ERROR.getValue();
        }
        Response response = new Response.Builder()
                .code(code)
                .msg(msg)
                .build();
        return response;
    }
}
