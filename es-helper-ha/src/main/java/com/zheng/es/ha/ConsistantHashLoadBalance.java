package com.zheng.es.ha;


import com.zheng.es.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  一致性hash策略
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月09日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ConsistantHashLoadBalance extends AbstractLoadBalance {
    /**
     * 每个key包含50个节点
     */
    private Integer replicaNums = 50;
    private TreeMap<Long, String> circle = new TreeMap<>();
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public String select(String index) {
        throw new UnsupportedOperationException("一致性hash算法不支持该操作");
    }

    @Override
    public String select(String key, List<String> clusterKeys) {
        if (StringUtil.isEmpty(clusterKeys)) {
            return null;
        }
        // 构建hash环
        for (String clusterKey : clusterKeys) {
            addNode(clusterKey);
        }
        // 根据key顺时针选择环上的节点
        Map.Entry<Long, String> entry = circle.ceilingEntry(hash(key));
        if (StringUtil.isEmpty(entry)) {
            entry = circle.firstEntry();
        }
        return entry.getValue();
    }

    private void addNode(String clusterKey) {
        lock.lock();
        try {
            for (int i = 0; i < replicaNums; i++) {
                circle.put(hash(clusterKey + i), clusterKey);
            }
        } finally {
            lock.unlock();
        }
    }

    public long hash(String key)
    {
        MessageDigest md5;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new IllegalStateException(e.getMessage(), e);
        }

        byte[] bytes;
        try
        {
            bytes = key.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalStateException(e.getMessage(), e);
        }

        md5.reset();
        md5.update(bytes);
        byte[] bKey = md5.digest();
        long result = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16 | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF));
        return result & 0xffffffffL;
    }
}
