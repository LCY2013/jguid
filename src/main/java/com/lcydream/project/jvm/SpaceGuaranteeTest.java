package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: 新生代到老年代空间分配担保
 *  -verbos:gc -XX:+PrintGCDetails -Xms20m -Xmx20m -Xmn10m -XX:+HandlePromotionFailure -XX:TargetSurvivorRatio=percent
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 09:48
 */
public class SpaceGuaranteeTest {

    public static void main(String[] args) {
        byte[] bytes1 = new byte[2*1024*1024];
        byte[] bytes2 = new byte[2*1024*1024];
        byte[] bytes3 = new byte[2*1024 * 1024];
        byte[] bytes4 = new byte[4*1024*1024];
        System.gc();
    }

}
