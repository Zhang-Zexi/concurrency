package com.mmall.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @ClassName RedisConfig
 * @Description
 * @Author zhangzx
 * @Date 2019/11/19 15:35
 * Version 1.0
 **/
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${jedis.host}") String host, @Value("${jedis.port}") int port) {
        return new JedisPool(host, port);
    }
}
