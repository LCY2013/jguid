package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: ++i i++ 有区别吗？谁的性能好?
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 10:46
 */
public class SelfAddTest {

    public static void add1(){
        for (int i=0;i<10;i++){
            System.out.println(i);
        }
    }

    public static void add2(){
        for (int i=0;i<10;i++){
            System.out.println(i);
        }
    }

}
