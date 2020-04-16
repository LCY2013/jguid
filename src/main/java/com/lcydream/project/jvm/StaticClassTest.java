package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: #{description}
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-16 14:49
 */
public class StaticClassTest {
/*  1 -> 3 -> 2
    static {
        System.out.println("1");
    }

    public StaticClassTest() {
        System.out.println("2");
    }

    static {
        System.out.println("3");
    }*/

    private static int i = 0;

    static {
        i = 1;
        System.out.println(i);
    }

    //private static int i = 0;

    public static void main(String[] args) {
        //new StaticClassTest();
        //System.out.println(StaticClassTest.i);
    }
}
