package com.zheng.es.start;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 *
 *  File:
 *
 *  Copyright (c) 2016, globalegrow.com All Rights Reserved.
 *
 *  Description:
 *  服务启动类
 *
 *  Revision History
 *  Date,					Who,					What;
 *  2019年04月16日			zhenglian			    Initial.
 *
 * </pre>
 */
public class ServerBootstrap {
    private Logger logger = LogManager.getLogger(this.getClass());
    private CountDownLatch latch = new CountDownLatch(1);
    private Thread daemonThread;
    private ServerBootstrap() {
        initDaemonThread();
    }
    
    private static class Inner {
        private static ServerBootstrap instance = new ServerBootstrap();
    }
    
    public static ServerBootstrap getInstance() {
        return Inner.instance;
    }
    
    public void start(String... configs) {
        // 初始化spring容器
        SearchApplicationContext.getInstance().init(configs);
        // 启动后台进程
        daemonThread.start();
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            latch.countDown();
            logger.info("latch count down now ...");
        }));
    }

    private void initDaemonThread() {
        daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("wait latch to count down...");
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 前台阻塞
        daemonThread.setDaemon(false);
        daemonThread.setName("server daemon thread");
    }


}
