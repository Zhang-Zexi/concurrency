package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
/**
 * @ClassName ImmutableExample1
 * @Description
 * @Author zhangzx
 * @Date 2019/11/16 19:30
 * Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2 );
        map.put(3,4 );
        map.put(5,6 );
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        map.put(1,3 );// 虽然不能修改引用，但是可以修改里面的值
        log.info("{}", map.get(1));
    }

    // 如果参数不允许修改，可以这样写
    private void test(final int a) {
//        a = 1;
    }
}
