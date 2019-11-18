package com.mmall.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockExample6
 * @Description
 * @Author zhangzx
 * @Date 2019/11/18 16:49
 * Version 1.0
 **/

@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();// 从ReentrantLock取出Condition

        new Thread(() -> {
            try {
                reentrantLock.lock();// 加入AQS等待队列
                log.info("wait signal"); // 1
                condition.await(); // 线程AQS队列移除，对应操作是锁的释放，加入conditon的等待队列中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();//线程1释放了锁，所以获取锁
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();// 发送信号，发现有一个线程1的节点，就取出来，加入AQS的等待队列中（线程1没有唤醒，只是放到结构图上面）
            log.info("send signal ~ "); // 3
            reentrantLock.unlock();
        }).start();
    }
}

