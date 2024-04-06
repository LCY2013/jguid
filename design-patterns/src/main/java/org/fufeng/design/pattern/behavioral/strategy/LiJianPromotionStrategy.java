package org.fufeng.design.pattern.behavioral.strategy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 10:39
 */
public class LiJianPromotionStrategy implements PromotionStrategy {

    @Override
    public void doPromotion()
    {
        System.out.println("立减促销,课程的价格直接减去配置的价格");
    }
}
