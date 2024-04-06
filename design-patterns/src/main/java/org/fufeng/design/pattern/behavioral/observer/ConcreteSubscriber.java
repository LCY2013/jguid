package org.fufeng.design.pattern.behavioral.observer;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.*;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 15:59
 */

// 具体观察者实现
public class ConcreteSubscriber implements Subscriber<String> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        // 设置订阅量的大小(Long.MAX_VALUE)
        subscription.request(Long.MAX_VALUE);
        //subscription.request(1); // 请求一个元素
        printf("Subscribed");
    }

    @Override
    public void onNext(String item) {
        //printf("Received: " + item);
        //subscription.request(1); // 请求下一个元素
        // 查看订阅的消息是否是退出指令
        if ("exit".equals(item)){
            // 取消订阅信息
            this.subscription.cancel();
            return;
        }else if ("exception".equalsIgnoreCase(item)){
            throw new RuntimeException("throw a exception");
        }
        printf("获取到数据->"+item);
    }

    @Override
    public void onError(Throwable throwable) {
        //throwable.printStackTrace();
        printf("onError ->"+throwable);
    }

    @Override
    public void onComplete() {
        printf("Done!");
    }

    /**
     *  打印方法抽象
     * @param object 打印对象
     */
    private static void printf(Object object){
        System.out.printf("Thread-[%s],(%s)\n",
                Thread.currentThread().getName(),object);
    }
}
