package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: Eden 区优先分配
 *  -verbose:gc -XX:+PrintGCDetails -Xmn10m -Xms20m -Xmx20m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=5m 大对象大小
 *  -XX:MaxTenuringThreshold=threshold
 * Sets the maximum tenuring threshold for use in adaptive GC sizing. The largest value is 15. The default value is 15 for the parallel (throughput) collector, and 6 for the CMS collector.
 * The following example shows how to set the maximum tenuring threshold to 10:
 *
 * -XX:MaxTenuringThreshold=10
 * <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#BGBCIEFC">java tool orders</a>
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 09:27
 */
public class EdenAllocatorTest {

    public static void main(String[] args) {
        byte[] bytes = new byte[6*1024*1024];
    }

}
