package com.lcydream.project.jguid.basics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddOperationTest {

    private static int sum = 0;
    private static long sumLong = 0;

    public static void main(String[] args) {
        for (int i=0;i<100000;i++){
            new Thread(()->{
                try {
                    TimeUnit.MICROSECONDS.sleep(new Random().nextInt(2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum++;
                sumLong++;
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println(sumLong);
    }

}
