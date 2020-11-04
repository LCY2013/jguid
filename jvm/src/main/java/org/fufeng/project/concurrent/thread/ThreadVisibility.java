package org.fufeng.project.concurrent.thread;

/**
 * ThreadVisibility
 * 测试多线程下面共享内存数据的可见性
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/7 9:59
 */
public class ThreadVisibility {
	private static Thread thread1 = new Thread(new Task());

	private static Thread thread2 = new Thread(new Task());

	private static Thread thread3 = new Thread(new Task());

	private static Thread thread4 = new Thread(new Task());

	private static Thread thread5 = new Thread(new Task());

	public static void main(String[] args) {
		/*thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();*/
		for(int i=0;i<1000;i++){
			new Thread(new Task()).start();
		}

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main:"+Task.volatileInt);
	}
}
