package com.lcydream.project.jguid.basics;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * {@link ThreadLocal} 示例
 */
public class ThreadLocalTest {

    static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        for (int i=0;i<10;i++){
            Thread thread = new Thread(runner,i+"");
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }
    }

    public static class Runner implements Runnable{
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("Thread name:"+Thread.currentThread().getName()
                + " default format:"+dateFormatThreadLocal.get().toPattern());
            Thread.sleep(new Random().nextInt(1000));
            dateFormatThreadLocal.set(new SimpleDateFormat());
            System.out.println("Thread name:"+Thread.currentThread().getName()
                    + " default format:"+dateFormatThreadLocal.get().toPattern());
        }
    }
}
