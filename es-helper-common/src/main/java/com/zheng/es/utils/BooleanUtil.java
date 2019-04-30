package com.zheng.es.utils;

import java.util.Objects;

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
 *  2019年04月30日			zhenglian			    Initial.
 *
 * </pre>
 */
public class BooleanUtil {
    public static Boolean formatBoolean(Object value) {
        if (!isBoolStr(value)) {
            return null;
        }
        return Boolean.valueOf(value.toString());
    }
    
    public static boolean isBoolStr(Object value) {
        if (StringUtil.isEmpty(value)) {
            return false;
        }
        String str = value.toString();
        return Objects.equals(str, "true") || Objects.equals(str, "false");
    }
}
