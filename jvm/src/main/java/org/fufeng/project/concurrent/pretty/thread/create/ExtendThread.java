package org.fufeng.project.concurrent.pretty.thread.create;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ExtendThread
 * @author: LuoChunYun
 * @Date: 2019/5/16 22:11
 * @Description: TODO
 */
public class ExtendThread extends Thread {

    @Override
    public void run() {
        System.out.println("ExtendThread");
    }

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
