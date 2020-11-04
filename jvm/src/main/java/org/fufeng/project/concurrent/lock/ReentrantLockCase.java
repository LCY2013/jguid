package org.fufeng.project.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockCase
 *  关于Lock的使用
 *   ReentrantLock 可重入锁
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/7 14:24
 */
public class ReentrantLockCase {

	private static int i = 0;
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		for(int k = 0;k<1000; k++ ){
			Thread thread = new Thread(()->{
				ReentrantLockCase lockCase = new ReentrantLockCase();
				try {
					//lockCase.lockCase();
					//lockCase.tryLockCase();
					//lockCase.tryLockCase(2);
					lockCase.lockInterruptibly();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			thread.start();
			if(k % 2 == 0){
				thread.interrupt();
			}
		}
	}

	void lockCase(){
		lock.lock();
		try {
			System.out.println(++i);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	void tryLockCase(){
		if(lock.tryLock()){
			try {
				System.out.println(++i);
			}finally {
				lock.unlock();
			}
		}else {
			System.out.println("么有获取到锁");
		}
	}

	void tryLockCase(long time) throws InterruptedException {
		if(lock.tryLock(time,TimeUnit.SECONDS)){
			try {
				System.out.println(++i);
				TimeUnit.SECONDS.sleep(time);
			}finally {
				lock.unlock();
			}
		}else {
			System.out.println("么有获取到锁");
		}
	}

	void lockInterruptibly() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		lock.lockInterruptibly();
		try {
			System.out.println(++i);
		}catch (Exception e){
			System.out.println("线程响应中断");
		}finally {
			lock.unlock();
		}

	}
}
