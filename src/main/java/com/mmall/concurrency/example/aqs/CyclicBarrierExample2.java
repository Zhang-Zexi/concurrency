package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @ClassName CyclicBarrierExample2
 * @Description
 * @Author zhangzx
 * @Date 2019/11/18 14:47
 * Version 1.0
 **/
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        executor.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);// barrier里面某个状态被调整过了，就会导致某个异常
        }catch (BrokenBarrierException e) {
            // 通过抛出异常的方法，可以让异常抛出，不影响主流程
            log.warn("BrokenBarrierException", e);
        }
        log.info("{} continue", threadNum);
    }
}
