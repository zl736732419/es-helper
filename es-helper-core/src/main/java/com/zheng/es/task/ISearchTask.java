package com.zheng.es.task;

import com.zheng.es.exceptions.SearchException;

import java.util.concurrent.Callable;

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
public interface ISearchTask<T> extends Callable<T> {
    T execute() throws SearchException;
}
