package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreExample1
 * @Description 多个许可
 * @Author zhangzx
 * @Date 2019/11/18 10:12
 * Version 1.0
 **/
@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i ++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    // 一次拿走了这多个许可，后面再向获取这个许可的时候，只能等待前面的许可使用完成了
                    semaphore.acquire(3); // 获取许可
                    test(threadNum);
                    semaphore.release(3); // 释放许可
                    Thread.sleep(1000);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        log.info("finish");
        exec.shutdown();
    }
    private static void test(int threadNum) throws Exception {
        log.info("{}", threadNum);
        Thread.sleep(10000);
    }
}
