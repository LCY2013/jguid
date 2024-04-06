package org.fufeng.design.pattern.behavioral.observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 16:00
 */
public class ConcreteFlowMain {
    public static void main(String[] args) {
        // 创建发布者和订阅者
        try (ConcretePublisher publisher = new ConcretePublisher()) {
            Flow.Subscriber<String> subscriber = new ConcreteSubscriber();

            // 订阅
            publisher.subscribe(subscriber);

            publisher.submit("fufeng");

            // 获取到执行器
            ExecutorService executorService =
                    (ExecutorService)publisher.getExecutor();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
