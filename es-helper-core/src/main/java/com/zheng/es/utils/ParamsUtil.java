package com.zheng.es.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.enums.EnumFieldType;
import com.zheng.es.field.CommonField;
import com.zheng.es.field.FilterField;
import com.zheng.es.model.Params;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
     * @param fileName
     * @return
     */
    public static Params transform(String fileName) {
        String jsonStr = readJsonFromResource(fileName);
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
            // option配置参数
            parseOptionFields(json, params);
            // 查询条件
            parseFilterFields(json, params);
        } catch (Exception e) {
            logger.error("params json format invalid, json: {}", jsonStr, e);
            return null;
        }
        return params;
    }

    private static void parseOptionFields(JsonObject json, Params params) {
        if (!json.has("options")) {
            return;
        }
        JsonObject options = (JsonObject) json.get("options");
        for (String key : options.keySet()) {
            params.addOption(key, getObjectValue(options.getAsJsonPrimitive(key)));
        }
    }

    private static Object getObjectValue(JsonPrimitive primitive) {
        if (primitive.isBoolean()) {
            return primitive.getAsBoolean();
        } else if (primitive.isNumber()) {
            if (primitive.getAsString().contains(".")) {
                return primitive.getAsDouble();
            } else {
                return primitive.getAsInt();
            }
        } else {
            return primitive.getAsString();
        }
    }

    private static String readJsonFromResource(String fileName) {
        String json = null;
        try {
            URL url = ParamsUtil.class.getClassLoader().getResource("json/" + fileName);
            File file = new File(url.toURI());
            json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    private static void parseFilterFields(JsonObject json, Params params) {
        if(!json.has("filters")) {
            return;
        }
        JsonArray filters = json.get("filters").getAsJsonArray();
        filters.forEach(filter -> {
            FilterField filterField = parseFilterFieldAttr((JsonObject) filter);
            params.addFilter(filterField);
        });
        
    }

    private static FilterField parseFilterFieldAttr(JsonObject json) {
        String field = null;
        if (json.has("field")) {
             field = json.get("field").getAsString();
        }
        String pair = null;
        if (json.has("pair")) {
            pair = json.get("pair").getAsString();
        }
        String operator = null;
        if (json.has("operator")) {
            operator = json.get("operator").getAsString();
        }
        EnumFieldType type = EnumFieldType.COMMON;
        if (json.has("type")) {
            String typeStr = json.get("type").getAsString();
            type = EnumFieldType.findByKey(typeStr);
            if (StringUtil.isEmpty(type)) {
                ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_INDEX_TYPE_NULL, field);
            }
        }
        FilterField filterField;
        switch (type) {
            case COMMON:
                default:
                filterField = parseCommonFilterField(json, field);
        }
        if (StringUtil.isNotEmpty(filterField)) {
            filterField.setPair(pair);
            filterField.setOperator(operator);
        }
        return filterField;
    }

    private static CommonField parseCommonFilterField(JsonObject json, String field) {
        if (!json.has("values")) {
            return null;
        }
        JsonArray array = json.get("values").getAsJsonArray();
        List<Object> values = new ArrayList<>();
        for (JsonElement value : array) {
            values.add(value.getAsString());
        }
        CommonField commonField = new CommonField(field, values);
        return commonField;
    }

    private static void parseCommonFields(JsonObject json, Params params) {
        // 索引名
        if (json.has("domain")) {
            String domain = json.get("domain").getAsString();
            params.setDomain(domain);
        }
        // accessToken
        if (json.has("accessToken")) {
            String accessToken = json.get("accessToken").getAsString();
            params.setAccessToken(accessToken);
        }
        // type
        if (json.has("type")) {
            String type = json.get("type").getAsString();
            params.setType(type);
        }
        // agent
        if (json.has("agent")) {
            String agent = json.get("agent").getAsString();
            params.setAgent(agent);
        }
        // version
        if (json.has("version")) {
            String version = json.get("version").getAsString();
            params.setVersion(version);
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
        // scrollId
        if (json.has("scrollId")) {
            String scrollId = json.get("scrollId").getAsString();
            params.setScrollId(scrollId);
        }
    }
}
