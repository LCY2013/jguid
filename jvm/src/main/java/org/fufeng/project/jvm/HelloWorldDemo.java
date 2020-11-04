package org.fufeng.project.jvm;

import java.io.File;

/**
 *
 * 数据、指令、控制
 *
 * @author LuoCY
 */
public class HelloWorldDemo {
    //常量、静态变量
    private final  int i = 0;
    //k=10
    private static int k = 0;
    //成员变量
    private Object obj = new Object();
    private int sss = 0;

    //局部变量
    public void methodOne(int i) {
        int j = 0;
        int sum = i + j;
        Object acb = obj;
        long start =System.currentTimeMillis();
        methodTwo();
        //TODO
        return;
        //正常
        //异常
    }

    public void methodTwo() {
        File file = new File("");
        //TODO
    }

    public static void methodThree() {
        methodThree();
    }

    public static void main(String[] args) {
        methodThree();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
