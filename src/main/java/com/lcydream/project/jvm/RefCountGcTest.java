package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: java是引用计数还是可达性算法？
 *  -verbose:gc -XX:+PrintGCDetails
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-14 22:35
 */
public class RefCountGcTest {

    private Object object;

    private byte[] bytes = new byte[1024*1024];

    public static void main(String[] args) {
        RefCountGcTest refCountGcTest1 = new RefCountGcTest();
        RefCountGcTest refCountGcTest2 = new RefCountGcTest();
        refCountGcTest1.object = refCountGcTest2;
        refCountGcTest2.object = refCountGcTest1;

        refCountGcTest1 = null;
        refCountGcTest2 = null;

        System.gc();
    }
}
