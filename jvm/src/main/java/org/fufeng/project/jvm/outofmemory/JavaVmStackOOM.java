package org.fufeng.project.jvm.outofmemory;

/**
 * JavaVmStackOOm 模拟stack区域的OutOfMemoryException
 * VM args: -Xss2M
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/26 21:59
 */
public class JavaVmStackOOM {

	private void dontSrop(){
		while (true){

		}
	}

	public void stackLeakByThread(){
		while (true){
			new Thread(()->dontSrop()).start();
		}
	}

	public static void main(String[] args) {
		JavaVmStackOOM javaVmStackOOM = new JavaVmStackOOM();
		javaVmStackOOM.stackLeakByThread();
	}
}
