package com.mmall.concurrency.example.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName GuavaCacheExample1
 * @Description
 * @Author zhangzx
 * @Date 2019/11/19 15:13
 * Version 1.0
 **/
@Slf4j
public class GuavaCacheExample1 {

    public static void main(String[] args) {

        LoadingCache<String, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10) // 最多存放10个数据
                .expireAfterWrite(10, TimeUnit.SECONDS) // 缓存10s
                .recordStats() // 开启记录状态数据功能
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return -1;
                    }
                });
        log.info("{}", cache.getIfPresent("key1")); // 没有命中返回null
        cache.put("key", 1);
        log.info("{}", cache.getIfPresent("key1"));
        cache.invalidate("key1");
        log.info("{}", cache.getIfPresent("key1"));

        try {
            log.info("{}", cache.getIfPresent("key2"));// -1
            cache.put("key2", 2);
            log.info("{}", cache.getIfPresent("key2"));// -2

            log.info("{}", cache.size());// 1

            for (int i = 3; i < 13; i ++) {
                cache.put("key" + i, i);
            }
            log.info("{}", cache.size()); // 10

            log.info("{}", cache.getIfPresent("key2"));// null

            Thread.sleep(11000);

            log.info("{}", cache.getIfPresent("key5"));

            log.info("{},{}", cache.stats().hitCount(), cache.stats().missCount());

            log.info("{},{}", cache.stats().hitRate(), cache.stats().missRate());
        } catch (Exception e) {

        }
    }
}
