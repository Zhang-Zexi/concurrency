package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.*;

/**
 * @ClassName TheadPoolExample3
 * @Description
 * @Author zhangzx
 * @Date 2019/11/18 19:56
 * Version 1.0
 **/
@Slf4j
public class TheadPoolExample4 {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        }, 3, TimeUnit.SECONDS);// 延迟3秒执行

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        }, 1,3 , TimeUnit.SECONDS);// 延迟1s，每隔3s执行任务
//        executorService.shutdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        },new Date(), 5 * 1000); // 间隔5s执行
    }
}
