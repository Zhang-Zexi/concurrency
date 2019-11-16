package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annotations.NotRecommend;
import com.mmall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Escape
 * @Description
 * @Author zhangzx
 * @Date 2019/11/16 16:59
 * Version 1.0
 **/
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCannBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCannBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
