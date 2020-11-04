package org.fufeng.project.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLockCase
 *  一个用来获取读锁，一个用来获取写锁。
 *  也就是说，将对临界资源的读写操作分成两个锁来分配给线程，
 *  从而使得多个线程可以同时进行读操作。
 *  下面的 ReentrantReadWriteLock 实现了 ReadWriteLock 接口
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/7 16:00
 */
public class ReadWriteLockCase {

	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		final ReadWriteLockCase readWriteLockCase = new ReadWriteLockCase();
		for(int i=0;i<5;i++) {
			new Thread(() -> {
				//readWriteLockCase.testWriteLock(Thread.currentThread());
				readWriteLockCase.testReadLock(Thread.currentThread());
			}).start();
		}
	}

	void testWriteLock(Thread thread){
		readWriteLock.writeLock().lock();
		try {
			long start = System.currentTimeMillis();
			System.out.println("线程" + thread.getName() + "开始写操作...");
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println("线程" + thread.getName() + "正在进行写操作...");
			}
			System.out.println("线程" + thread.getName() + "写操作完毕...");
		}finally {
			readWriteLock.writeLock().unlock();
		}
	}

	void testReadLock(Thread thread){
		readWriteLock.readLock().lock();
		try {
			long start = System.currentTimeMillis();
			System.out.println("线程" + thread.getName() + "开始读操作...");
			while (System.currentTimeMillis() - start <= 1) {
				System.out.println("线程" + thread.getName() + "正在进行读操作...");
			}
			System.out.println("线程" + thread.getName() + "读操作完毕...");
		}finally {
			readWriteLock.readLock().unlock();
		}
	}
}
