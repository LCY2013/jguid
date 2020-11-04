package org.fufeng.project.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * HeapOOM
 *  测试heap的OutOfMemory
 *  VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *  -Xms20m starting 初始化为20m
 *  -Xmx20m max 最大为20m，不让虚拟机进行动态扩容
 *  -XX:+HeapDumpOnOutOfMemoryError 虚拟机出现内存泄露异常时Dump出当前的内存堆转储快照以便事后进行分析
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/26 21:22
 */
public class HeapOOM {

	static class OOMObject{}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while (true){
			/*try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			list.add(new OOMObject());
		}
	}
}
