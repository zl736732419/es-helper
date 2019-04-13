package com.zheng.es.base;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.model.EsQuery;
import com.zheng.es.model.EsSearchResponse;
import com.zheng.es.model.Index;
import com.zheng.es.model.Params;
import com.zheng.es.model.QueryParams;
import com.zheng.es.model.Response;
import com.zheng.es.model.Type;
import com.zheng.es.parser.IEsQueryParser;
import com.zheng.es.service.IEsSearcher;
import com.zheng.es.utils.IndexConfigUtil;
import com.zheng.es.utils.SignUtil;
import com.zheng.es.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  调用base模块的查询
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月12日			zhenglian			    Initial.
 *
 * </pre>
 */
@Component
public class BaseSearcher implements IBaseSearcher {
    private Logger logger = LogManager.getLogger(this.getClass());
    private IndexConfigUtil config = IndexConfigUtil.getInstance();
    
    @Autowired
    private IEsQueryParser esQueryParser;
    @Autowired
    private IEsSearcher esSearcher;
    
    @Override
    public Response search(Params params) {
        QueryParams queryParams = buildQueryParams(params);
        EsQuery esQuery = esQueryParser.parse(queryParams);
        if (StringUtil.isEmpty(esQuery)) {
            logger.error("search error, esQuery is null");
            return null;
        }
        EsSearchResponse searchResponse = esSearcher.search(esQuery);
        return parseResult(params, searchResponse);
    }

    private Response parseResult(Params params, EsSearchResponse searchResponse) {
        if (StringUtils.isEmpty(searchResponse) || !searchResponse.isSuccess()) {
            return Response.buildEmptyResponse();
        }

        Response.Builder builder = new Response.Builder();
        Response response = builder.domain(params.getDomain())
                .type(params.getType())
                .code(searchResponse.getStatus().getStatus())
                .msg(EnumExceptionCode.SUCCESS.getValue())
                .pageNo(params.getPageNo())
                .pageSize(params.getPageSize())
                .build();
        // 解析文档数据
        parseHits(searchResponse.getSearchHits(), response);
        // 解析聚合数据
        parseAggs(searchResponse.getAggregations(), response);
        return response;
    }

    private void parseAggs(Aggregations aggregations, Response response) {
        // TODO
    }

    private void parseHits(SearchHits searchHits, Response response) {
        if (StringUtil.isEmpty(searchHits)) {
            return;
        }
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> source;
        for(SearchHit hit : searchHits) {
            source = hit.getSourceAsMap();
            if (StringUtil.isNotEmpty(source)) {
                data.add(source);
            }
        }
        response.setData(data);
    }

    private QueryParams buildQueryParams(Params params) {
        String domain = params.getDomain();
        Index index = config.getIndex(params.getDomain());
        Type type = index.getType(params.getType());
        List<String> fields = type.getAgentFields(params.getAgent());
        QueryParams.Builder builder = new QueryParams.Builder();
        String preference = SignUtil.uniqueKeyWithoutPage(params);
        builder.domain(domain)
                .type(params.getType())
                .pageNo(params.getPageNo())
                .pageSize(params.getPageSize())
                .scrollId(params.getScrollId())
                .preference(preference)
                .fields(fields)
                .filters(params.getFilters())
                .queryScoreEnable(params.isQueryScoreEnable())
                .logEnable(params.isQueryLogEnable())
                .key(preference);
        return builder.build();
    }
}
