package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: 内存计算
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-14 21:06
 */
public class MemoryTest {

    public static void main(String[] args) {
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long maxMemory = Runtime.getRuntime().maxMemory();
        final long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("freeMemory "+freeMemory+"字节,"+freeMemory/(1024.0D*1024)+"MB");
        System.out.println("maxMemory "+maxMemory+"字节,"+maxMemory/(1024.0D*1024)+"MB");
        System.out.println("totalMemory "+totalMemory+"字节,"+totalMemory/(1024.0D*1024)+"MB");
    }

}
