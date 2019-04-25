package com.zheng.es.model;

import com.zheng.es.utils.NumberUtil;
import com.zheng.es.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 *  2019年04月08日			zhenglian			    Initial.
 *
 * </pre>
 */
public class Cluster {
    private String name;
    private List<ClusterNode> nodes = new ArrayList<>();
    private static final Integer DEFAULT_PORT = 9200;
    private String indexes;

    private Cluster() {}
    
    public static class Builder {
        private Cluster cluster;
        public Builder() {
            cluster = new Cluster();
        }
        
        public Builder name(String name) {
            cluster.name = name;
            return this;
        }
        
        public Builder nodes(List<ClusterNode> nodes) {
            cluster.nodes = nodes;
            return this;
        }
        
        public Builder node(ClusterNode node) {
            cluster.nodes.add(node);
            return this;
        }
        
        public Builder nodes(String url) {
            if (StringUtil.isEmpty(url)) {
                return this;
            }
            String[] hostAndPorts = url.split(",");
            Arrays.stream(hostAndPorts)
                    .filter(hostAndPort -> StringUtil.isNotEmpty(hostAndPort))
                    .forEach(hostAndPort -> {
                        ClusterNode node = parseNode(hostAndPort);
                        node(node);
                    });
            return this;
        }

        private ClusterNode parseNode(String hostAndPort) {
            String[] arr = hostAndPort.split(":");
            String host = arr[0];
            Integer port = DEFAULT_PORT;
            if (arr.length == 2) {
                port = NumberUtil.parseInt(arr[1]);
            }
            ClusterNode node = new ClusterNode(host, port);
            return node;
        }

        public Builder indexes(String indexes) {
            cluster.indexes = indexes;
            return this;
        }
        
        public Cluster build() {
            return cluster;
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClusterNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ClusterNode> nodes) {
        this.nodes = nodes;
    }

    public String getIndexes() {
        return indexes;
    }

    public void setIndexes(String indexes) {
        this.indexes = indexes;
    }
}
