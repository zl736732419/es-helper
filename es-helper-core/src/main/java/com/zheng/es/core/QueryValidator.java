package com.zheng.es.core;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.enums.EnumFieldType;
import com.zheng.es.enums.EnumFieldValueType;
import com.zheng.es.enums.EnumQueryType;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.field.CommonField;
import com.zheng.es.field.FilterField;
import com.zheng.es.model.Field;
import com.zheng.es.model.Index;
import com.zheng.es.model.Params;
import com.zheng.es.model.Type;
import com.zheng.es.utils.EsConstants;
import com.zheng.es.utils.ExceptionUtil;
import com.zheng.es.utils.IndexConfigUtil;
import com.zheng.es.utils.NumberUtil;
import com.zheng.es.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  查询参数验证器
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月12日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class QueryValidator implements IValidator {
    private IndexConfigUtil config = IndexConfigUtil.getInstance();

    @Override
    public void validate(Params params) throws EsSearchException {
        if (StringUtil.isEmpty(params)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.PARAMS_EMPTY, null);
        }
        validateCommonFields(params);
        validateFilters(params);
    }

    /**
     * 验证filter
     *
     * @param params
     */
    private void validateFilters(Params params) {
        List<FilterField> filters = params.getFilters();
        if (StringUtil.isEmpty(filters)) {
            return;
        }
        for (FilterField filter : filters) {
            if (StringUtil.isEmpty(filter)) {
                ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_NOT_NULL, null);
            }
            validateFilterField(params, filter);
        }
    }

    private void validateFilterField(Params params, FilterField filterField) {
        String fieldName = filterField.getField();
        if (StringUtil.isEmpty(fieldName)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_NULL, fieldName);
        }
        Index index = config.getIndex(params.getDomain());
        Type type = index.getType(params.getType());
        Field field = type.getField(fieldName);
        if (StringUtil.isNotEmpty(field)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_NOT_EXIST, fieldName);
        }
        List<String> filterFields = type.getQueryTypeFields(EnumQueryType.TERM.getValue());
        if (!filterFields.contains(fieldName)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_NOT_SUPPORT_QUERY, fieldName);
        }
        EnumFieldType enumFieldType = filterField.getFieldType();
        switch (enumFieldType) {
            // TODO other filter field custom validate, ex:range, date,
            case COMMON:
            default:
                validateCommonFilterField((CommonField) filterField, field);
        }
    }

    private void validateCommonFilterField(CommonField commonField, Field field) {
        List<Object> values = commonField.getValues();
        if (StringUtil.isEmpty(values)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_COMMON_FIELD_VALUES_NULL, field.getName());
        }
        String type = field.getType();
        String fieldName = field.getName();
        values.stream()
                .forEach(value -> validateFieldValue(value, type, fieldName));

    }

    private void validateFieldValue(Object value, String type, String field) {
        EnumFieldValueType valueType = EnumFieldValueType.findByValue(type);
        if (StringUtil.isEmpty(valueType)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_INDEX_TYPE_NULL, field);
        }
        if (StringUtil.isEmpty(value)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_INDEX_TYPE_NULL, field);
        }
        switch (valueType) {
            case STRING:
                // no validate for string
                break;
            case DOUBLE:
                validateDoubleValue(value, field);
                break;
            case INT:
                validateIntValue(value, field);
                break;
            case LONG:
                validateLongValue(value, field);
                break;
            default:
                ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_INDEX_TYPE_UNSUPPORTED, field);
        }
    }

    private void validateLongValue(Object value, String field) {
        if (value.toString().contains(".")) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_LONG_TYPE, field);
        }
        Long num = NumberUtil.parseLong(value);
        if (StringUtil.isEmpty(num)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_LONG_TYPE, field);
        }

    }

    private void validateIntValue(Object value, String field) {
        if (value.toString().contains(".")) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_INT_TYPE, field);
        }
        Integer num = NumberUtil.parseInt(value);
        if (StringUtil.isEmpty(num)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_INT_TYPE, field);
        }
    }

    private void validateDoubleValue(Object value, String field) {
        Double num = NumberUtil.formatDouble(value, 4);
        if (StringUtil.isEmpty(num)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_FILTER_FIELD_DOUBLE_TYPE, field);
        }
    }

    /**
     * 验证普通字段
     *
     * @param params
     */
    private void validateCommonFields(Params params) {
        // domain
        if (StringUtils.isEmpty(params.getDomain())) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_DOMAIN_NULL, "domain");
        }
        Index index = config.getIndex(params.getDomain());
        if (StringUtil.isEmpty(index)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_DOMAIN_NOT_EXIST, "domain");
        }
        // type
        Type type = index.getType(params.getType());
        if (StringUtil.isEmpty(type)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_TYPE_NOT_EXIST, "type");
        }
        // pageNo
        Integer pageNo = params.getPageNo();
        Integer pageSize = params.getPageSize();
        if (pageSize > EsConstants.MAX_PAGE_SIZE) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_PAGE_SIZE_INVALID, "page");
        }
        // max record num
        Integer recordNum = pageNo * pageSize;
        if (recordNum > EsConstants.MAX_SIZE) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_RECORD_OVERFLOW, "page");
        }
        // agent
        String agents = index.getAgents();
        if (!agents.contains(params.getAgent() + ",")) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.VALID_AGENT_NOT_EXIST, "agent");
        }
    }
}
