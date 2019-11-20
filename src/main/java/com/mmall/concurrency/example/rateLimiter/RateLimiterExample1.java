package com.mmall.concurrency.example.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RateLimiterExample1
 * @Description
 * @Author zhangzx
 * @Date 2019/11/19 17:55
 * Version 1.0
 **/
@Slf4j
public class RateLimiterExample1 {

    // 其实这个是用了guava里的令牌桶算法，一开始只放了一个令牌，所以只打印了一个数
    private static RateLimiter rateLimiter = RateLimiter.create(5);

    public static void main(String[] args) throws Exception {

        for (int index = 0; index < 100; index ++) {
            Thread.sleep(1000);
            if (rateLimiter.tryAcquire()) {
                handle(index);
            }
        }
    }

    private  static void handle(int i) {
        log.info("{}", i);
    }
}
