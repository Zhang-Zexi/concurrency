package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * @ClassName SingletonExample2
 * @Description  恶汉模式 类装载的时候创建
 * @Author zhangzx
 * @Date 2019/11/16 17:16
 * Version 1.0
 **/
@ThreadSafe
public class SingletonExample2 {

    // 私有的构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
 }
