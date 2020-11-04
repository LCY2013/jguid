package org.fufeng.project.concurrent.pretty.thread.deadlock;

/**
 * @ClassName: ThreadDeadlock
 * @author: LuoChunYun
 * @Date: 2019/5/19 9:56
 * @Description: TODO
 *  死锁的四个条件：
 *   1、互斥条件
 *   2、请求并持有条件
 *   3、不可剥夺条件
 *   4、环路等待条件
 */
public class ThreadDeadlock {

    /**
     * 模拟线程资源
     */
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        deadLockOne();
    }

    private static void deadLockOne() {
        //创建线程A
        final Thread threadA = new Thread(() -> {
            synchronized (resourceA){
                System.out.println("threadA get resourceA lock");
                synchronized (resourceB){
                    System.out.println("threadA get resourceA lock");
                }
            }
            System.out.println("threadA is over");
        });

        /*final Thread threadB = new Thread(() -> {
            synchronized (resourceB){
                System.out.println("threadB get resourceB lock");
                synchronized (resourceA){
                    System.out.println("threadB get resourceA lock");
                }
            }
            System.out.println("threadB is over");
        });*/

        //破坏死锁的顺序性
        final Thread threadB = new Thread(() -> {
            synchronized (resourceA){
                System.out.println("threadB get resourceA lock");
                synchronized (resourceB){
                    System.out.println("threadB get resourceB lock");
                }
            }
            System.out.println("threadB is over");
        });

        threadA.start();
        threadB.start();

        System.out.println("main thread is over");
    }
}
