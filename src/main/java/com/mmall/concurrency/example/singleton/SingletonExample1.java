package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotThreadSafe;

/**
 * @ClassName SingletonExample1
 * @Description  懒汉模式 在第一次使用的时候创建
 * @Author zhangzx
 * @Date 2019/11/16 17:16
 * Version 1.0
 **/
@NotThreadSafe
public class SingletonExample1 {

    // 私有的构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
 }
