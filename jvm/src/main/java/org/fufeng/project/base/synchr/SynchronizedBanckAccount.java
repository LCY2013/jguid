package org.fufeng.project.base.synchr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SynchronizedBanckAccount
 * @author: LuoChunYun
 * @Date: 2019/5/22 14:18
 * @Description: TODO
 *
 *  银行账户，A 200 , B 200 , C 200
 *    A -> B 100
 *    B -> C 100
 *    期望：A 100 , B 200 , C 300
 */
public class SynchronizedBanckAccount {

    private int balance;

    public SynchronizedBanckAccount(int amt) {
        this.balance = amt;
    }

    /**
     * 这里targetAccount账户的操作式不安全的
     * @param targetAccount 目标账户
     * @param amt 转账金额
     */
    synchronized void transfer(SynchronizedBanckAccount targetAccount,int amt){
        if(this.balance > amt) {
            this.balance -= amt;
            try {
                TimeUnit.MILLISECONDS.sleep(Math.round(50));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            targetAccount.balance += amt;
        }
    }

    public static void main(String[] args) {
        //新建三个用户
        SynchronizedBanckAccount A = new SynchronizedBanckAccount(200);
        SynchronizedBanckAccount B = new SynchronizedBanckAccount(200);
        SynchronizedBanckAccount C = new SynchronizedBanckAccount(200);
        /*final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            A.transfer(B,100);
        });
        executorService.execute(()->{
            B.transfer(C,100);
        });
        executorService.shutdown();
        */

        new Thread(()->A.transfer(B,100)).start();
        new Thread(()->B.transfer(C,100)).start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("A:"+A.balance+",B:"+B.balance+",C:"+C.balance);
    }
}
