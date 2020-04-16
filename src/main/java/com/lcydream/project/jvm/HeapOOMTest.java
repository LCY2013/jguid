package com.lcydream.project.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jguid
 * @description: 堆内存溢出
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-14 21:19
 */
public class HeapOOMTest {

    byte[] bytes = new byte[1*1024*1024];

    public static void main(String[] args) {
        List<HeapOOMTest> list = new ArrayList<>();
        int counter = 0;
        try {
            while (true) {
                list.add(new HeapOOMTest());
                ++counter;
            }
        }catch (Throwable e){
            System.out.println("counter:"+counter);
            e.printStackTrace();
        }
    }
}
