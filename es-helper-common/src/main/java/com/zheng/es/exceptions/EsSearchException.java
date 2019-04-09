package com.zheng.es.exceptions;

import com.zheng.es.enums.EnumExceptionCode;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询异常
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年03月26日			zhenglian			    Initial.
 *
 * </pre>
 */
public class EsSearchException extends RuntimeException {
    /**
     * 异常码
     * @see com.zheng.es.enums.EnumExceptionCode
     */
    private Integer code;
    
    public EsSearchException(EnumExceptionCode exceptionCode) {
        this(exceptionCode.getKey(), exceptionCode.getValue());
    }
    
    public EsSearchException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public EsSearchException(Integer code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }
    
    public EsSearchException(String message, Throwable e) {
        super(message, e);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
