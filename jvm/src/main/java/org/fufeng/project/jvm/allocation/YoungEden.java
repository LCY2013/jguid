package org.fufeng.project.jvm.allocation;

/**
 * @ClassName: YoungEden
 * @author: LuoChunYun
 * @Date: 2019/5/17 14:42
 * @Description: TODO
 *  VM args: -verbose:gc -XX:+PrintGCDetails JDK1.8默认-XX:+UseParallelGC
 *  启用SerialGC：-verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
 *  PretenureSizeThreshold 设置大对象的内存大小 -XX:PretenureSizeThreshold=4M
 *  设置对象年龄大小：-XX:MaxTenuringThreshold=1
 *
 */
public class YoungEden {

    public static void main(String[] args) {
        byte[] bytes = new byte[4*1024*1024];

        System.gc();
    }
}
