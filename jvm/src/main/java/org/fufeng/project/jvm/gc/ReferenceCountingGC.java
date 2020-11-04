package org.fufeng.project.jvm.gc;

/**
 * ReferenceCountingGC 测试循环引用是否会被回收
 * VM: -verbose:gc -Xloggc:gc.log  -XX:+PrintHeapAtGC
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 13:55
 */
public class ReferenceCountingGC {

	/**
	 * 设置几个成员变量以便查看GC日志
	 */
	public Object instance = null;

	public static final int _1M = 1024*1024;

	public static void main(String[] args) {
		testGc();
	}

	static void testGc(){
		ReferenceCountingGC referenceCountingGCA = new ReferenceCountingGC();
		ReferenceCountingGC referenceCountingGCB = new ReferenceCountingGC();
		referenceCountingGCA.instance = referenceCountingGCB;
		referenceCountingGCB.instance = referenceCountingGCA;

		referenceCountingGCA=null;
		referenceCountingGCB=null;

		//主动申请系统调用GC，但是不一定会发生
		System.gc();
	}
}
