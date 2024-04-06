package org.fufeng.design.pattern.behavioral.strategy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 10:40
 */
public class FanxianPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销,返回的金额转到用户账户");
    }

}
