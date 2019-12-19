package com.mmall.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


/**
 * @ClassName CountExample
 * @Description
 * @Author zhangzx
 * @Date 2019/11/13 16:12
 * Version 1.0
 **/
@Slf4j
public class CountExample {

//    HashMap hashMap = new HashMap<>(10);
    private static int threadTotal = 200;
    private static int clientTotal = 5000;
    private static long count = 0;

    public static void main(String[] args) {
        //最近阿里发布的 Java开发手册中强制线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险
//        ExecutorService executorService1 = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index ++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count ++;
    }
}
