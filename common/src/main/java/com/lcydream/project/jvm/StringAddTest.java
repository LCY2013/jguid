package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: String 加法操作
 *  javap -verbose *.class
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 10:35
 */
public class StringAddTest {

    public static void add1(){
        long startTime = System.currentTimeMillis();
        String str = "";
        for (int i=0;i<100000;i++){
            str += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public static void add2(){
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<100000;i++){
            sb.append(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    public static void main(String[] args) {
        add1();
        add2();
    }

}
