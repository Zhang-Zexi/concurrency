package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrency.annotations.ThreadSafe;

/**
 * @ClassName ImmutableExample3
 * @Description
 * @Author zhangzx
 * @Date 2019/11/16 20:07
 * Version 1.0
 **/
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);// 只要初始化需要，就可一直写多个参数

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map =ImmutableMap.of(1, 2, 3, 4);// 参数成对出现

    private final static ImmutableMap<Integer, Integer> map2 =ImmutableMap.<Integer,Integer>builder().put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
//        set.add(4 );
//        map.put(1, 4);
        System.out.println(map2.get(3));
    }
}
