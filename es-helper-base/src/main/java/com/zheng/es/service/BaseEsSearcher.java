package com.zheng.es.service;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.EsQuery;
import com.zheng.es.model.EsSearchResponse;
import com.zheng.es.utils.ClientPool;
import com.zheng.es.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
 *  es查询
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
@Service
public class BaseEsSearcher implements IEsSearcher {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private IClusterService clusterService;
    private ClientPool pool = ClientPool.getInstance();
    
    @Override
    public EsSearchResponse search(EsQuery esQuery) {
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
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        // index & type
        String index = esQuery.getIndex();
        searchRequest.indices(index);
        searchRequest.types(esQuery.getType());
        // preference
        searchRequest.preference(esQuery.getPreference());
        searchRequest.source(sourceBuilder);
        
        // 获取执行es查询的集群key
        String clusterKey = clusterService.getClusterKey(esQuery.getKey(), index);
        if (StringUtil.isEmpty(clusterKey)) {
            throw new EsSearchException(EnumExceptionCode.INDEX_NOT_EXISTS.getKey(), "索引[" + index + "]不存在");
        }
        RestHighLevelClient client = pool.getClient(clusterKey);
        SearchResponse response;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            throw new RuntimeException("elasticsearch error: clusterKey=" + clusterKey, e);
        }
        if (null == response) {
            return null;
        }
        return new EsSearchResponse.Builder()
                .status(response.status())
                .took(response.getTook())
                .searchHits(response.getHits())
                .aggregations(response.getAggregations())
                .scrollId(response.getScrollId())
                .build();
    }
}
