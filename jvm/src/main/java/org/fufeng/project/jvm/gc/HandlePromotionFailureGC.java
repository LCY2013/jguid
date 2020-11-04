package org.fufeng.project.jvm.gc;

/**
 * HandlePromotionFailureGC 分配担保失败
 *  测试老年代担保分配内存容量失败的情况
 *  VM ages: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *  -XX:-PromotionFailureALot debugJVM的参数
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/4 11:24
 */
public class HandlePromotionFailureGC {

	private final static int _1M = 1024*1024;

	@SuppressWarnings("unused")
	static void testHandlePromotion(){
		byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,
				allocation7;
		allocation1 = new byte[2 * _1M];
		//什么时候进老年代由-XX:MaxTenuringThreshold决定
		allocation2 = new byte[2 * _1M];
		allocation3 = new byte[2 * _1M];
		allocation1 = null;
		allocation4 = new byte[2 * _1M];
		allocation5 = new byte[2 * _1M];
		allocation6 = new byte[2 * _1M];
		allocation4 = null;
		allocation5 = null;
		allocation6 = null;
		allocation7 = new byte[2 * _1M];
	}

	public static void main(String[] args) {
		testHandlePromotion();
	}
}
