package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * @ClassName SingletonExample1
 * @Description  懒汉模式 双重同步锁单例模式
 * @Author zhangzx
 * @Date 2019/11/16 17:16
 * Version 1.0
 **/
@NotThreadSafe
public class SingletonExample4 {

    // 私有的构造函数
    private SingletonExample4() {

    }

    // CPU的指令 ，当我们进行instance = new SingletonExample4();的时候
    //1、分配对象内存空间 memory = allocate()
    //2、初始化对象ctorInstance()
    //3、instance = memeory 设置instance指向刚分配的内存
    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态的工厂方法
    // 性能开销太大
    public static SingletonExample4 getInstance() {
        if (instance == null) { //  双重检测机制
            synchronized (SingletonExample4.class) { // 同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
 }
