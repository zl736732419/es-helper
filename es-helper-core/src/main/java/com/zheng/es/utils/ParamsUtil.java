package com.zheng.es.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zheng.es.field.FilterField;
import com.zheng.es.model.Params;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
public class ParamsUtil {
    private static Logger logger = LogManager.getLogger(ParamsUtil.class); 

    /**
     * json字符串转化为Params对象
     * @param jsonStr
     * @return
     */
    public static Params transform(String jsonStr) {
        logger.debug("start parsing json string: \n{}", jsonStr);
        if (StringUtil.isEmpty(jsonStr)) {
            return null;
        }
        Params params = new Params();
        params.setOriginalJson(jsonStr);
        Gson gson = new Gson();
        try {
            JsonObject json = gson.fromJson(jsonStr, JsonObject.class);
            // 普通参数
            parseCommonFields(json, params);
            // 查询条件
            parseFilterFields(json, params);
        } catch (Exception e) {
            logger.error("params json format invalid, json: {}", jsonStr, e);
            return null;
        }
        return params;
    }

    private static void parseFilterFields(JsonObject json, Params params) {
        if(!json.has("filters")) {
            return;
        }
        JsonArray filters = json.get("filters").getAsJsonArray();
        filters.forEach(filter -> {
            FilterField filterField = new FilterField();
            parseFilterFieldAttr(json, filterField);
            // TODO other filter field
            params.addFilter(filterField);
        });
        
    }

    private static void parseFilterFieldAttr(JsonObject json, FilterField filterField) {
        if (json.has("field")) {
            String field = json.get("field").getAsString();
            filterField.setField(field);
        }
        if (json.has("values")) {
            JsonArray values = json.get("values").getAsJsonArray();
            List<Object> list = new ArrayList<>();
            for (JsonElement value : values) {
                list.add(value.getAsString());
            }
            filterField.setValues(list);
        }
        if (json.has("pair")) {
            String pair = json.get("pair").getAsString();
            filterField.setPair(pair);
        }
    }

    private static void parseCommonFields(JsonObject json, Params params) {
        // 索引名
        if (json.has("domain")) {
            String domain = json.get("domain").getAsString();
            params.setDomain(domain);
        }
        // type
        if (json.has("type")) {
            String type = json.get("type").getAsString();
            params.setType(type);
        }
        // 分页信息
        if (json.has("pageNo")) {
            Integer pageNo = json.get("pageNo").getAsInt();
            params.setPageNo(pageNo);
        }
        if (json.has("pageSize")) {
            Integer pageSize = json.get("pageSize").getAsInt();
            params.setPageSize(pageSize);
        }
    }
}
