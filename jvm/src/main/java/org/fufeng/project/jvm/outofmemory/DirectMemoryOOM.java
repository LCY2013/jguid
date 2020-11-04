package org.fufeng.project.jvm.outofmemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DirectMemoryOOM
 * VM Args: -Xms20M -XX:MaxDirectMemorySize=10M
 * 设置最大直接内存
 *
 * <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html#BGBCIEFC">java 参数信息<a/>
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 11:55
 */
public class DirectMemoryOOM {

	private static final int _1M = 1024*1024;

	public static void main(String[] args) throws IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while (true){
			unsafe.allocateMemory(_1M);
		}
	}
}
