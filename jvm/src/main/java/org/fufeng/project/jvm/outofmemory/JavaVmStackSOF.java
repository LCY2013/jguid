package org.fufeng.project.jvm.outofmemory;

/**
 * JavaVmStackSOF 模拟java.lang.StackOverflowError异常
 * VM Args: -Xss128k 规定栈大小
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/26 21:50
 */
public class JavaVmStackSOF {

	private int stackLength = 1;

	public void stackLeak(){
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVmStackSOF javaVmStackSOF = new JavaVmStackSOF();
		try {
			javaVmStackSOF.stackLeak();
		}catch (Throwable e){
			System.out.println("stack length : " + javaVmStackSOF.stackLength);
			throw e;
		}
	}
}
