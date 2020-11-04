package org.fufeng.project.base.synchr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Allocator
 * @author: LuoChunYun
 * @Date: 2019/5/22 16:01
 * @Description: TODO
 *
 * 破坏占用且等待条件
 *
 * 对应到编程领域，“同时申请”这个操作是一个临界区，我们也需要一个角色（Java 里面的类）来管理这个临界区，
 * 我们就把这个角色定为 Allocator。它有两个重要功能，分别是：同时申请资源 apply() 和同时释放资源 free()。
 * 账户 Account 类里面持有一个 Allocator 的单例（必须是单例，只能由一个人来分配资源）。
 * 当账户 Account 在执行转账操作的时候，首先向 Allocator 同时申请转出账户和转入账户这两个资源，
 * 成功后再锁定这两个资源；当转账操作执行完，释放锁之后，
 * 我们需通知 Allocator 同时释放转出账户和转入账户这两个资源。具体的代码实现如下。
 *
 * 破坏不可抢占条件
 * 破坏不可抢占条件看上去很简单，核心是要能够主动释放它占有的资源，这一点 synchronized 是做不到的。
 * 原因是 synchronized 申请资源的时候，如果申请不到，线程直接进入阻塞状态了，而线程进入阻塞状态，
 * 啥都干不了，也释放不了线程已经占有的资源。
 * 你可能会质疑，“Java 作为排行榜第一的语言，这都解决不了？”你的怀疑很有道理，
 * Java 在语言层次确实没有解决这个问题，不过在 SDK 层面还是解决了的，java.util.concurrent
 * 这个包下面提供的 Lock 是可以轻松解决这个问题的。关于这个话题，咱们后面会详细讲。
 */
class Allocator {

    private static Allocator allocator;

    private void Account(){
    }

    public static Allocator getInstance(){
        synchronized (Allocator.class) {
            if (allocator == null) {
                synchronized (Allocator.class) {
                    allocator = new Allocator();
                }
            }
        }
        return allocator;
    }

    private List<Object> als =
            new ArrayList<>();
    // 一次性申请所有资源
    synchronized boolean apply(
            Object from, Object to){
        if(als.contains(from) ||
                als.contains(to)){
            return false;
        } else {
            als.add(from);
            als.add(to);
        }
        return true;
    }
    // 归还资源
    synchronized void free(
            Object from, Object to){
        als.remove(from);
        als.remove(to);
    }

    public static void main(String[] args) {
        Account A = new Account(200);
        Account B = new Account(200);
        Account C = new Account(200);

        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            A.transfer(B,100);
        });
        executorService.execute(()->{
            B.transfer(A,100);
        });
        executorService.shutdown();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(A.balance+":"+B.balance);
    }
}

class Account {
    // actr 应该为单例
    private Allocator actr;
    public int balance;

    public Account(int acm) {
        this.balance = acm;
        this.actr = Allocator.getInstance();
    }

    // 转账
    void transfer(Account target, int amt){
        final long time = System.nanoTime();
        // 一次性申请转出账户和转入账户，直到成功
        while(!actr.apply(this, target)){
            if(System.nanoTime() > System.nanoTime() + 5){
                break;
            }
        }
        try{
            // 锁定转出账户
            synchronized(this){
                // 锁定转入账户
                synchronized(target){
                    if (this.balance > amt){
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }
}
