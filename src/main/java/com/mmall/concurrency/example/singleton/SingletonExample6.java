package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * @ClassName SingletonExample1
 * @Description  懒汉模式 双重同步锁单例模式
 * @Author zhangzx
 * @Date 2019/11/16 17:16
 * Version 1.0
 **/
@ThreadSafe
public class SingletonExample6 {

    // 私有的构造函数
    private SingletonExample6() {

    }

    // 单例对象
    private static SingletonExample6 instance = null;

    // 静态块
    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
 }
