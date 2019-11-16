package com.mmall.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SynchronizedExample11
 * @Description
 * @Author zhangzx
 * @Date 2019/11/16 15:50
 * Version 1.0
 **/
@Slf4j
public class SynchronizedExample11 extends SynchronizedExample1{

    // 修饰一个方法
    public void test2(int j) {
        for (int i = 0; i < 10; i ++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample11 example11 = new SynchronizedExample11();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example11.test2(1);
        });
        executorService.execute(() -> {
            example11.test2(2);
        });

    }
}
