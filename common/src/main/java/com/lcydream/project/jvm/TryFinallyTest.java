package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: try finally 测试
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 10:54
 */
public class TryFinallyTest {

    public static String try01(){
        String str = "luo";
        try {
            return str;
        }finally {
            str = "chun";
        }
    }

    public static String try02(){
        String str = "luo";
        try {
            return str;
        }finally {
            return "chun";
        }
    }

    public static void main(String[] args) {
        System.out.println(try01());
        System.out.println(try02());
    }
}
