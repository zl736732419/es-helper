package com.zheng.es.service;

import com.zheng.es.model.EsQuery;
import com.zheng.es.model.QueryParams;
import com.zheng.es.parser.BaseEsQueryParser;
import com.zheng.es.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
@Service
public class BaseSearchImpl implements IBaseSearch {
    private Logger logger = LogManager.getLogger(this.getClass());
    
    @Autowired
    private BaseEsQueryParser esQueryParser;
    @Autowired
    private IClusterService clusterService;
    
    @Override
    public SearchResponse search(QueryParams params) throws Exception {
        EsQuery esQuery = esQueryParser.parse(params);
        if (StringUtil.isEmpty(esQuery)) {
            logger.error("search error, esQuery is null");
            return null;
        }
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // page info
        sourceBuilder.from(esQuery.getFrom());
        sourceBuilder.size(esQuery.getSize());
        // timeout
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // query
        sourceBuilder.query(esQuery.getQueryBuilder());
        // source fields
        List<String> fields = esQuery.getFields();
        String[] includes = fields.toArray(new String[fields.size()]);
        sourceBuilder.fetchSource(includes, null);
        // explain
        sourceBuilder.explain(esQuery.isExplainEnable());
        
        SearchRequest searchRequest = new SearchRequest();
        // index & type
        searchRequest.indices(esQuery.getIndex());
        searchRequest.types(esQuery.getType());
        // preference
        searchRequest.preference(esQuery.getPreference());
        searchRequest.source(sourceBuilder);
        
        // 获取执行es查询的集群key
        String key = esQuery.getKey();
        String clusterKey = clusterService.getClusterKey(key, esQuery.getIndex());
        
        

        return null;
    }
}
