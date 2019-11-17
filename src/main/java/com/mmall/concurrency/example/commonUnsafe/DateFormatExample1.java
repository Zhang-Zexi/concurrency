package com.mmall.concurrency.example.commonUnsafe;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName DateFormatExample1
 * @Description
 * @Author zhangzx
 * @Date 2019/11/17 12:43
 * Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class DateFormatExample1 {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();// 此方法保证count必减为0
        executorService.shutdown();
    }

    private static void update() {
        try {
            simpleDateFormat.parse("20180208" );
        } catch (ParseException e) {
            log.error("parse exception", e);
        }
    }
}
