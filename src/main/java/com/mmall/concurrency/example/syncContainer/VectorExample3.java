package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * @ClassName ArrayListExample
 * @Description
 * @Author zhangzx
 * @Date 2019/11/17 13:40
 * Version 1.0
 **/
@Slf4j
@NotThreadSafe
public class VectorExample3 {

    // ConcurrentModificationException
    private static void test1(Vector<Integer> v1) { // foreach
        for (Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);// 如果想要remove在遍历完之后
            }
        }
    }

    // ConcurrentModificationException
    private static void test2(Vector<Integer> v1) { // iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i); // 如果想要remove在遍历完之后
            }
        }
    }

    // success
    private static void test3(Vector<Integer> v1) { // for
        for (int i = 0; i < v1.size(); i ++) {
            if (v1.equals(3)) {
                v1.remove(i);
            }
        }
    }
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test2(vector);
    }
}
