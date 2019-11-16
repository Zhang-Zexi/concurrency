package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.Recommend;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * @ClassName SingletonExample7
 * @Description 枚举模式 最安全
 * @Author zhangzx
 * @Date 2019/11/16 17:16
 * Version 1.0
 **/
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造方法
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只被调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
 }
