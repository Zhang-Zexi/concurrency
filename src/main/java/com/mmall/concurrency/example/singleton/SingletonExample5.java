package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annotations.NotThreadSafe;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * @ClassName SingletonExample1
 * @Description  懒汉模式 双重同步锁单例模式
 * @Author zhangzx
 * @Date 2019/11/16 17:16
 * Version 1.0
 **/
@ThreadSafe
public class SingletonExample5 {

    // 私有的构造函数
    private SingletonExample5() {

    }

    // CPU的指令 ，当我们进行instance = new SingletonExample4();的时候
    //1、分配对象内存空间 memory = allocate()
    //2、初始化对象ctorInstance()
    //3、instance = memeory 设置instance指向刚分配的内存

    //多线程可能会出现指令重排
    // JVM和CPU优化，发生了指令重排(指令重排不再按照上面来了)，变成
    //1、分配对象内存空间 memory = allocate()
    //3、instance = memeory 设置instance指向刚分配的内存
    //2、初始化对象ctorInstance()

    // 单例对象
    // 使用volatile限制指令重排
    private volatile static SingletonExample5 instance = null;

    // 静态的工厂方法
    // 性能开销太大
    public static SingletonExample5 getInstance() {
        if (instance == null) { //  双重检测机制      // B
            synchronized (SingletonExample5.class) { // 同步锁
                if (instance == null) {
                    instance = new SingletonExample5(); // A - 3
                    // 如果此时发生了指令重排序，线程A执行了3，没有执行2
                    // 此时B处发现已经分配好instance了，就拿来使用
                    // 但是这个时候是没有初始化对象的
                    // 所以就会出错，线程是不安全的（虽然发生的概率不大，但是也是不安全）
                }
            }
        }
        return instance;
    }
 }
