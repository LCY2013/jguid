package org.fufeng.project.base.synchr;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: Account
 * @author: LuoChunYun
 * @Date: 2019/5/22 16:26
 * @Description: TODO
 *
 * 破坏循环等待条件
 * 破坏这个条件，需要对资源进行排序，然后按序申请资源。这个实现非常简单，我们假设每个账户都有不同的属性 id，
 * 这个 id 可以作为排序字段，申请的时候，我们可以按照从小到大的顺序来申请。
 * 比如下面代码中，①~⑥处的代码对转出账户（this）和转入账户（target）排序，
 * 然后按照序号从小到大的顺序锁定账户。这样就不存在“循环”等待了。
 */
public class AccountOrder {

    private int id;
    private int balance;

    public AccountOrder(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    // 转账
    void transfer(AccountOrder target, int amt){
        //按id大小给账户排序
        AccountOrder left = this;
        AccountOrder right = target;

        //ID排序
        if(left.id > target.id){
            left = right;
            right = this;
        }

        synchronized (left){
            synchronized (right){
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }

    public static void main(String[] args) {
        //模拟数据
        AccountOrder A = new AccountOrder(1,100);
        AccountOrder B = new AccountOrder(1,100);

        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            A.transfer(B,100);
        });
        executorService.execute(()->{
            B.transfer(A,100);
        });
        executorService.shutdown();

    }
}
