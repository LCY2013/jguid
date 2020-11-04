package org.fufeng.project.jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

/**
 * RuntimeConstantPoolOOM
 *  VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *  通过设置永久代的大小，间接设置方法区的大小,但是8以后没有永久代,所以以本地内存为主
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 10:37
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		//通过list保持常量池的引用，避免FullGC对这部分数据进行回收
		List<String> list = new ArrayList<>();
		//通过向常量池中添加Integer的数据，让它出现OOM，Integer的数据量足以让10M的方法区溢出
		int i=0;
		while (true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
