package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CountDownLatchExample1
 * @Description
 * @Author zhangzx
 * @Date 2019/11/18 10:12
 * Version 1.0
 **/
@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i ++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();// 计数器不断减一减一，直到0
                }
            });
        }
        // await()有个好处，如果忘了调用shutdown方法的时候也是能停的
        countDownLatch.await(10, TimeUnit.MICROSECONDS);// 可以指定等待时间(数值，单位ms)
        log.info("finish");
        exec.shutdown();// 并不会立即销毁线程，而是让当前已有的线程执行完，之后销毁线程池

    }
    private static void test(int threadNum) throws Exception {
        Thread.sleep(100);
        log.info("{}", threadNum);
    }
}
