package com.mmall.concurrency.example.threadLocal;

/**
 * @ClassName RequestHolder
 * @Description
 * @Author zhangzx
 * @Date 2019/11/17 11:33
 * Version 1.0
 **/
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
