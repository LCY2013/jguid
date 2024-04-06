package org.fufeng.design.pattern.behavioral.strategy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 10:39
 */
public class ManJianPromotionStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("满减优惠");
    }

}
