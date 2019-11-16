package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annotations.NotThreadSafe;
import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName ConcurrencyTest
 * @Description
 * @Author zhangzx
 * @Date 2019/11/15 17:33
 * Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class CountExample4 {

    public static int clientTotal = 5000;
    public static int threadTotal = 200;
    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();// 此方法保证count必减为0
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count ++;
        // 1.从主存取出count
        // 2. +1
        // 3. 重新写入主存
    }
}
