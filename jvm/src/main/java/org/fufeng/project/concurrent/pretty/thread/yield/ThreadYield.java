package org.fufeng.project.concurrent.pretty.thread.yield;

import org.fufeng.project.concurrent.fatory.ThreadPoolFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ThreadYield
 * @author: LuoChunYun
 * @Date: 2019/5/18 21:33
 * @Description: 测试线程的yield
 *  大家都知道现代计算机CPU执行单元是线程，而多个线程是通过时间片的轮询执行，
 *   这个时间间隔很短，所有看起来像所有线程都是同时执行，这里也引出了两个概念：
 *      1、并行：就是指多个任务在同一时刻执行
 *      2、并发：就是指多个任务在某一个时间段内交替执行
 *   yield是一个利用让出CPU的执行时间，然后让线程处于就绪状态让线程调度器重新进行下一次时间片的轮询，
 *   这个过程还是可能轮询到自己，所以这是一个概率问题不是一个必然问题。
 */
public class ThreadYield implements Runnable {

    /**
     * 构造方法启动线程
     */
    /*public ThreadYield() {
        Thread thread = new Thread(this);
        thread.start();
    }*/

    @Override
    public void run() {
        //模拟线程让出时间片
        for (int i=0;i<5;i++){
            //开始执行
            System.out.println(Thread.currentThread().getName()+" start do something "+i);
            //模拟偶数执行，奇数呼叫线程下次时间片切换
            //if(i%2==0) {
                //奇数线程切换
                Thread.yield();
            //}
        }
    }

    public static void main(String[] args) {
        doSomething();
    }

    /**
     *  没有yield
     * fufeng1226-pool-0 start do something 0
     * fufeng1226-pool-0 start do something 1
     * fufeng1226-pool-0 start do something 2
     * fufeng1226-pool-0 start do something 3
     * fufeng1226-pool-0 start do something 4
     * fufeng1226-pool-1 start do something 0
     * fufeng1226-pool-1 start do something 1
     * fufeng1226-pool-1 start do something 2
     * fufeng1226-pool-1 start do something 3
     * fufeng1226-pool-1 start do something 4
     * fufeng1226-pool-3 start do something 0
     * fufeng1226-pool-3 start do something 1
     * fufeng1226-pool-3 start do something 2
     * fufeng1226-pool-3 start do something 3
     * fufeng1226-pool-3 start do something 4
     * fufeng1226-pool-2 start do something 0
     * fufeng1226-pool-2 start do something 1
     * fufeng1226-pool-2 start do something 2
     * fufeng1226-pool-2 start do something 3
     * fufeng1226-pool-2 start do something 4
     */
    /**
     * yield 后
     * fufeng1226-pool-0 start do something 0
     * fufeng1226-pool-0 start do something 1
     * fufeng1226-pool-0 start do something 2
     * fufeng1226-pool-0 start do something 3
     * fufeng1226-pool-2 start do something 0
     * fufeng1226-pool-2 start do something 1
     * fufeng1226-pool-2 start do something 2
     * fufeng1226-pool-2 start do something 3
     * fufeng1226-pool-2 start do something 4
     * fufeng1226-pool-1 start do something 0
     * fufeng1226-pool-3 start do something 0
     * fufeng1226-pool-1 start do something 1
     * fufeng1226-pool-0 start do something 4
     * fufeng1226-pool-3 start do something 1
     * fufeng1226-pool-3 start do something 2
     * fufeng1226-pool-3 start do something 3
     * fufeng1226-pool-3 start do something 4
     * fufeng1226-pool-1 start do something 2
     * fufeng1226-pool-1 start do something 3
     * fufeng1226-pool-1 start do something 4
     */
    private static void doSomething() {
        final ExecutorService executorService =
                Executors.newCachedThreadPool(new ThreadPoolFactory());
        executorService.execute(new ThreadYield());
        executorService.execute(new ThreadYield());
        executorService.execute(new ThreadYield());
        executorService.execute(new ThreadYield());
        executorService.shutdown();
    }
}
