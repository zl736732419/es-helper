package com.zheng.es.core;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.enums.EnumFieldType;
import com.zheng.es.enums.EnumQueryType;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.field.FilterField;
import com.zheng.es.model.Field;
import com.zheng.es.model.Index;
import com.zheng.es.model.Params;
import com.zheng.es.model.Type;
import com.zheng.es.utils.EsConstants;
import com.zheng.es.utils.IndexConfigUtil;
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
            throw new EsSearchException(EnumExceptionCode.PARAMS_EMPTY);
        }
        validateCommonFields(params);
        validateFilters(params);
    }

    /**
     * 验证filter
     * @param params
     */
    private void validateFilters(Params params) {
        List<FilterField> filters = params.getFilters();
        if (StringUtil.isEmpty(filters)) {
            return;
        }
        for (FilterField filter : filters) {
            if (StringUtil.isEmpty(filter)) {
                throw new EsSearchException(EnumExceptionCode.VALID_FILTER_NOT_NULL);
            }
            validateFilterField(params, filter);
        }
    }

    private void validateFilterField(Params params, FilterField filter) {
        String fieldName = filter.getField();
        if (StringUtil.isEmpty(fieldName)) {
            throw new EsSearchException(EnumExceptionCode.VALID_FILTER_FIELD_NULL);
        }
        Index index = config.getIndex(params.getDomain());
        Type type = index.getType(params.getType());
        Field field = type.getField(fieldName);
        if (StringUtil.isNotEmpty(field)) {
            throw new EsSearchException(EnumExceptionCode.VALID_FILTER_FIELD_NOT_EXIST);
        }
        List<String> filterFields = type.getQueryTypeFields(EnumQueryType.TERM.getValue());
        if (!filterFields.contains(fieldName)) {
            throw new EsSearchException(EnumExceptionCode.VALID_FILTER_FIELD_NOT_SUPPORT_QUERY);
        }
        EnumFieldType enumFieldType = filter.getFieldType();
        switch (enumFieldType) {
            case COMMON:
                break;
                // TODO other filter field custom validate, ex:range, date,
            default:
        }
    }

    /**
     * 验证普通字段
     * @param params
     */
    private void validateCommonFields(Params params) {
        // domain
        if (StringUtils.isEmpty(params.getDomain())) {
            throw new EsSearchException(EnumExceptionCode.VALID_DOMAIN_NULL);
        }
        Index index = config.getIndex(params.getDomain());
        if (StringUtil.isEmpty(index)) {
            throw new EsSearchException(EnumExceptionCode.VALID_DOMAIN_NOT_EXIST);
        }
        // type
        Type type = index.getType(params.getType());
        if (StringUtil.isEmpty(type)) {
            throw new EsSearchException(EnumExceptionCode.VALID_TYPE_NOT_EXIST);
        }
        // pageNo
        Integer pageNo = params.getPageNo();
        Integer pageSize = params.getPageSize();
        if (pageSize > EsConstants.MAX_PAGE_SIZE) {
            throw new EsSearchException(EnumExceptionCode.VALID_PAGE_SIZE_INVALID);
        }
        // max record num
        Integer recordNum = pageNo * pageSize;
        if (recordNum > EsConstants.MAX_SIZE) {
            throw new EsSearchException(EnumExceptionCode.VALID_RECORD_OVERFLOW);
        }
        // agent
        String agents = index.getAgents();
        if (!agents.contains(params.getAgent()+",")) {
            throw new EsSearchException(EnumExceptionCode.VALID_AGENT_NOT_EXIST);
        }
    }
}
