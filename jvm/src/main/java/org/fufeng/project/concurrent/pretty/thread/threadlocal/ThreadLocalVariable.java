package org.fufeng.project.concurrent.pretty.thread.threadlocal;

/**
 * @ClassName: ThreadLocalVariable
 * @author: LuoChunYun
 * @Date: 2019/5/19 15:20
 * @Description: TODO
 *  ThreadLocal 测试
 */
public class ThreadLocalVariable {

    /**
     * 创建ThreadLocal变量
     *  {@link ThreadLocal}
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     *  打印函数
     * @param tlVal ThreadLocal的值
     */
    static void print(String tlVal){
        //1.打印当前ThreadLocal中的值
        System.out.println(tlVal + ":" + threadLocal.get());
        //2.清除当前线程ThreadLocal的值
        threadLocal.remove();
    }

    public static void main(String[] args) {
        //创建线程A
        final Thread threadA = new Thread(() -> {
            //设置当前线程的ThreadLocal的值
            threadLocal.set("this is ThreadA value");
            //调用函数print
            print("ThreadA");
            //打印ThreadLocal的值
            System.out.println("threadA remove after : " + threadLocal.get());
        });

        //创建线程B
        final Thread threadB = new Thread(() -> {
            //设置当前线程的ThreadLocal的值
            threadLocal.set("this is ThreadB value");
            //调用函数print
            print("ThreadB");
            //打印ThreadLocal的值
            System.out.println("threadB remove after : " + threadLocal.get());
        });

        //启动子线程
        threadA.start();
        threadB.start();
    }

}
