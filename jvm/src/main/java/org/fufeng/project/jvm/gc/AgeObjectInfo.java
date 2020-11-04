package org.fufeng.project.jvm.gc;

/**
 * AgeObjectInfo
 *
 *  VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:MaxTenuringThreshold=1 -XX:+PrintGCDetails
 *
 *  设置对象年龄大小：-XX:MaxTenuringThreshold=1
 *   -XX:+PrintTenuringDistribution
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 17:50
 */
public class AgeObjectInfo {

	private final static int _1M = 1024*1024;

	@SuppressWarnings("unused")
	static void allocation(){
		byte[] allocation1,allocation2,allocation3;
		allocation1 = new byte[_1M/4];
		//什么时候进老年代由-XX:MaxTenuringThreshold决定
		allocation2 = new byte[4 * _1M];
		allocation3 = new byte[4 * _1M];
		allocation3 = null;
		allocation3 = new byte[4 * _1M];
	}

	public static void main(String[] args) {
		allocation();
	}

}
