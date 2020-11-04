package org.fufeng.project.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread
 * 保证线程顺序执行的两种方式
 * join
 * exectorServer
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/5 20:57
 */
public class ThreadOrder {

	private static Thread thread1 = new Thread(()->{
		System.out.println("thred1");
	});

	private static Thread thread2 = new Thread(()->{
		System.out.println("thred2");
	});

	private static Thread thread3 = new Thread(()->{
		System.out.println("thred3");
	});

	private static ExecutorService executorService = Executors.newSingleThreadExecutor();

	public static void main(String[] args) throws InterruptedException {
		//join控制 -> Object.wait() 控制
		/*thread1.start();
		thread1.join();
		thread2.start();
		thread2.join();
		thread3.start();*/

		//ExecutorService 利用FIFO的队列实现线程的有序性
		executorService.submit(thread1);
		executorService.submit(thread2);
		executorService.submit(thread3);
		executorService.shutdown();
	}
}
