package org.fufeng.project.concurrent.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Task
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/7 10:00
 */
public class Task implements Runnable {

	/**
	 * 模拟一个多线程不可见的数据
	 */
	static Integer NOT = 0;

	static volatile int volatileInt = 0;

	/**
	 * 模拟一个多线程可见的数据
	 * volatile 原子性，可见性，不能做到复合操作的原子性
	 */
	static volatile Integer YES = 0;

	/**
	 * 模拟一个多线程可见
	 * synchronized 可重入锁，互斥，可见
	 */
	private static Integer YES_SYN = 0;

	static Random random = new Random();

	@Override
	public void run() {
		/*try {
			TimeUnit.SECONDS.sleep(random.nextInt(3));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//System.out.println("NOT:"+(++NOT));
		//System.out.println("YES"+(++YES));
		//System.out.println("YES_SYN"+setYES_syn());
		System.out.println(++volatileInt);
	}

	public synchronized static Integer setYES_syn() {
		return ++Task.YES_SYN;
	}
}
