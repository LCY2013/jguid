package org.fufeng.project.concurrent.pretty.thread.create;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ImplRunable
 * @author: LuoChunYun
 * @Date: 2019/5/16 22:13
 * @Description: TODO
 */
public class ImplRunable implements Runnable {

    @Override
    public void run() {
        System.out.println("ImplRunable");
    }

    public static void main(String[] args) {
        ImplRunable implRunable = new ImplRunable();
        new Thread(implRunable).start();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
