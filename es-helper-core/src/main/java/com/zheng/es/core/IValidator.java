package com.zheng.es.core;

import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Params;

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
 *  2019年04月12日			zhenglian			    Initial.
 *
 * </pre>
 */
public interface IValidator {
    void validate(Params params) throws EsSearchException;
}
