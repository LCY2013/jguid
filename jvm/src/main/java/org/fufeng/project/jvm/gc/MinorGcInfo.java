package org.fufeng.project.jvm.gc;

/**
 * MinorGcInfo 测试查看MinorGc信息
 *  VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 17:12
 */
public class MinorGcInfo {

	private final static int _1M = 1024*1024;

	static void allocation(){
		byte[] allocation1,allocation2,allocation3,allocation4,allocation5;
		allocation1 = new byte[2 * _1M];
		allocation2 = new byte[2 * _1M];
		allocation3 = new byte[2 * _1M];
		//allocation4 = new byte[2 * _1M];
		//这里出现一次minorGC
		allocation5 = new byte[4 * _1M];
	}

	public static void main(String[] args) {
		allocation();
	}
}
