package org.fufeng.project.lamda.concrrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: VolatileTest
 * @author: LuoChunYun
 * @Date: 2019/5/10 15:26
 * @Description: TODO
 */
public class VolatileTest {

    private static volatile int volatileInt = 0;
    private static volatile Integer volatileInteger = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(10000);
    //private static CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for (int i=0;i<10000;i++) {
            executorService.submit(()->{
                volatileInt++;
                volatileInteger++;
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        System.out.println(volatileInt);
        System.out.println(volatileInteger);

        executorService.shutdown();

    }


}
