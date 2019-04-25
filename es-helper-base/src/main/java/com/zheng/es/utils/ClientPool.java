package com.zheng.es.utils;

import com.zheng.es.enums.EnumExceptionCode;
import com.zheng.es.exceptions.EsSearchException;
import com.zheng.es.model.Cluster;
import com.zheng.es.model.ClusterNode;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ClientPool {
    /**
     * es连接池
     */
    private Map<String, RestHighLevelClient> clientMap = new ConcurrentHashMap<>();
    private ClusterPool clusterPool = ClusterPool.getInstance();
    
    private ClientPool() {
        initClients();
    }
    
    public RestHighLevelClient getClient(String key) {
        return clientMap.get(key);
    }
    
    public void close() {
        if (StringUtil.isEmpty(clientMap)) {
            return;
        }
        clientMap.values().stream()
                .forEach(client -> close(client));
    }
    
    private void close(RestHighLevelClient client) {
        if (null == client) {
            return;
        }
        try {
            client.close();
        } catch (IOException e) {
            throw new EsSearchException(EnumExceptionCode.ES_CLIENT_ERROR.getKey(), "close client error.", e);
        }
    }

    private void initClients() {
        Map<String, Cluster> clusters = clusterPool.getClusters();
        if (StringUtil.isEmpty(clusters)) {
            ExceptionUtil.handleValidateException(EnumExceptionCode.CLUSTER_NULL, null);
        }
        clusters.forEach((key, cluster) -> {
            List<HttpHost> nodes = getHttpPorts(cluster.getNodes());
            if (StringUtil.isEmpty(nodes)) {
                return;
            }
            RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(
                            nodes.toArray(new HttpHost[nodes.size()])
                    )
            );
            clientMap.put(key, client);
        });
    }

    private List<HttpHost> getHttpPorts(List<ClusterNode> nodes) {
        if (StringUtil.isEmpty(nodes)) {
            return null;
        }
        List<HttpHost> hosts = new ArrayList<>();
        HttpHost host;
        for (ClusterNode node : nodes) {
            host = new HttpHost(node.getHost(), node.getPort());
            hosts.add(host);
        }
        return hosts;
    }

    private static class Inner {
        private static ClientPool instance = new ClientPool();
    }
    
    public static ClientPool getInstance() {
        return Inner.instance;
    }
}
