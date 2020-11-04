package org.fufeng.project.concurrent.pretty.thread.threadlocal;

/**
 * @ClassName: InheritableThreadLocalVariable
 * @author: LuoChunYun
 * @Date: 2019/5/19 15:55
 * @Description: TODO
 */
public class InheritableThreadLocalVariable {

    /**
     * 创建ThreadLocal变量
     *  {@link ThreadLocal}
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 创建ThreadLocal变量
     *  {@link ThreadLocal}
     */
    private static ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("main threadLocal value");
        inheritableThreadLocal.set("main inheritableThreadLocal value");

        //模拟子线程获取不到父线程ThreadLocal的信息
        notGetThreadLocalInfo();
        //模拟获取得到父线程中的ThreadLocal信息
        canGetThreadLocalInfo();

    }

    /**
     * 在这里ThreadLocal是一个操作工具，用户操作当前运行时的线程的  ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
     *  这里是可以通用的原因是main线程是thread-0线程的父线程，具体代码{@link Thread#init(ThreadGroup, Runnable, String, long, java.security.AccessControlContext, boolean)}
     *  这里会判断 父线程中inheritableThreadLocals 这个变量是否为空，不为空就会将这个引用复制给子线程，所以子线程是可以使用父线程中的ThreadLocal
     */
    private static void canGetThreadLocalInfo() {
        //创建线程A
        final Thread thread = new Thread(() -> {
            //打印ThreadLocal的值
            System.out.println("canGetThreadLocalInfo : " + inheritableThreadLocal.get());
        });

        //启动子线程
        thread.start();
    }

    /**
     * 在这里ThreadLocal是一个操作工具，用户操作当前运行时的线程的 ThreadLocal.ThreadLocalMap threadLocals = null;
     * 这里在不用的线程中是不通用的，原因是这里存在两个线程，一个main，一个thread-0，所以子线程不能够获取到主线程放入threadLocal中的值
     * 详细可以看{@link Thread}中对threadLocals这个成员变量的初始化
     */
    private static void notGetThreadLocalInfo() {
        //创建线程A
        final Thread thread = new Thread(() -> {
            //打印ThreadLocal的值
            System.out.println("notGetThreadLocalInfo : " + threadLocal.get());
        });

        //启动子线程
        thread.start();
    }

}
