package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName ArrayListExample
 * @Description
 * @Author zhangzx
 * @Date 2019/11/17 13:40
 * Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class ArrayListExample {
    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();// 此方法保证count必减为0
        executorService.shutdown();
        log.info("size:{}", list.size());
    }

    private static void update(int i) {
        list.add(i);
    }
}
