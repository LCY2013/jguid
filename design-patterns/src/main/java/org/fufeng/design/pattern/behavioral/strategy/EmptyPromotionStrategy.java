package org.fufeng.design.pattern.behavioral.strategy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 10:50
 */
public class EmptyPromotionStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
