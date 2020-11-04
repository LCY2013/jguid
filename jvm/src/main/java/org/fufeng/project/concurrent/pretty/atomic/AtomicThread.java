package org.fufeng.project.concurrent.pretty.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: AutomicThread
 * @author: LuoChunYun
 * @Date: 2019/5/19 21:28
 * @Description: TODO
 *  利用CAS非阻塞算法实现原子操作
 */
public class AtomicThread {

    /**
     * 定义一个CAS操作的对象
     */
    private static AtomicLong atomicLong = new AtomicLong();

    /**
     * 定义一个数组5个0
     */
    private static int[] arrayOne = new int[]{0,5,3,6,8,4,6,9,0,0,6,0,3,5,0};

    /**
     * 定义一个数组6个0
     */
    private static int[] arrayTwo = new int[]{0,5,3,0,8,4,6,9,0,0,6,0,3,5,0};

    public static void main(String[] args) {
        final ExecutorService executorService =
                Executors.newCachedThreadPool();
        //计算第一个数组的0个数
        executorService.execute(()->{
            for (int i=0;i<arrayOne.length;i++){
                if(arrayOne[i] == 0){
                    atomicLong.incrementAndGet();
                }
            }
        });
        //计算第二个数组0的个数
        executorService.execute(()->{
            for (int i=0;i<arrayTwo.length;i++){
                if(arrayTwo[i] == 0){
                    atomicLong.incrementAndGet();
                }
            }
        });

        executorService.shutdown();
        System.out.println(atomicLong.get());
    }


}
