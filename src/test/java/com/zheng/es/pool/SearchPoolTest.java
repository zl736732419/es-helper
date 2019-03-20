package com.zheng.es.pool;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

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
 *  2019年03月20日			zhenglian			    Initial.
 *
 * </pre>
 */
public class SearchPoolTest {
    @Test
    public void testPool() throws Exception {
        ExecutorService executor = SearchPool.getInstance().getExecutor();

        CompletableFuture<Object> completableFuture = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i + 1);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }, executor);
        List<CompletableFuture> futures = new ArrayList<>();
        futures.add(completableFuture);
        CompletableFuture<Void> finishedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture<List<Object>> result = finishedFuture.thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));
        List<Object> objects = result.get();
    }
}
