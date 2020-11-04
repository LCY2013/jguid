package org.fufeng.project.jvm.gc;

import java.util.concurrent.TimeUnit;

/**
 * FinalizeEscapeGC
 *  1.完成对象在GC标记时的自救
 *  2.这种自救只能一次，因为finalize这个方法最多被系统调用一次
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 14:22
 */
public class FinalizeEscapeGC {

	static FinalizeEscapeGC SAVE_HOOK = null;

	FinalizeEscapeGC local = null;

	int result = 0;

	public void isAlive(){
		System.out.println("yes , i`m still alive!");
	}

	/**
	 * 测试类静态变量是否可以在方法参数中传递
	 *  结果：不能够
	 *
	 * @param finalizeEscapeGC 对象
	 */
	static void save(FinalizeEscapeGC finalizeEscapeGC) throws InterruptedException {
		finalizeEscapeGC = null;
		System.gc();
		TimeUnit.SECONDS.sleep(1);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else {
			System.out.println("i`m dead!");
		}
	}

	/**
	 * 测试成员变量是否可以在方法参数中传递
	 *  结果：
	 *
	 * @param finalizeEscapeGC 对象
	 */
	void saveInstace(FinalizeEscapeGC finalizeEscapeGC) throws InterruptedException {
		finalizeEscapeGC.setResult(1);
		finalizeEscapeGC = null;
		System.gc();
		TimeUnit.SECONDS.sleep(2);
		if(local != null){
			local.isAlive();
		}else {
			System.out.println("i`m dead!");
		}
	}

	static void saveSelf() throws InterruptedException {
		//第一次拯救自己
		SAVE_HOOK = null;
		//申请触发一次GC活动
		System.gc();

		//因为finalize方法由优先级低的线程执行，所以将主线程休眠0.5秒
		TimeUnit.SECONDS.sleep(1);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else {
			System.out.println("i`m dead!");
		}
	}

	/*@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method execute!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}*/

	public static void main(String[] args) throws InterruptedException {
		//SAVE_HOOK = new FinalizeEscapeGC();
		//save(SAVE_HOOK);
		FinalizeEscapeGC finalizeEscapeGC = new FinalizeEscapeGC();
		finalizeEscapeGC.setLocal(finalizeEscapeGC);
		finalizeEscapeGC.saveInstace(finalizeEscapeGC);
		System.out.println(finalizeEscapeGC.getResult());

		//第一次自杀然后拯救自己
		//saveSelf();
		//第二次自杀然后拯救自己
		//saveSelf();
	}

	public void setLocal(FinalizeEscapeGC local) {
		this.local = local;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}
}
