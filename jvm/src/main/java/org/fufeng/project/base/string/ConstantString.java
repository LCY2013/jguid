package org.fufeng.project.base.string;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ConstantString
 * @author: LuoChunYun
 * @Date: 2019/5/22 10:20
 * @Description: TODO
 */
public class ConstantString {

    public static void main(String[] args) {
        String strOne = "abc";
        String strTwo = "abc";

        String strThree = new String("abc");
        String strFour = new String("abc");

        compareStr(strOne,strTwo);
        compareStr(strFour,strThree);

        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            synchronizedStr(strOne);
        });
        executorService.execute(()->{
            synchronizedStr(strTwo);
        });
        executorService.execute(()->{
            synchronizedStr(strThree);
        });
        executorService.execute(()->{
            synchronizedStr(strFour);
        });
        executorService.execute(()->{
            synchronizedStr("a");
        });
        executorService.shutdown();
    }

    private static void synchronizedStr(String str) {
        final String intern = str.intern();
        synchronized (intern){
            System.out.println(Thread.currentThread().getName()+":"+str);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void compareStr(String strOne, String strTwo) {
        System.out.println(strOne == strTwo);
    }


}
