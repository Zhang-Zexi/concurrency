package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

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

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        for(int i = 0; i < vector.size(); i ++) {
            vector.get(i);
            System.out.println(vector.get(i));
        }
    }
}
