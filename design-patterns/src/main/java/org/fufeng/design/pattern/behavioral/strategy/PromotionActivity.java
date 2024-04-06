package org.fufeng.design.pattern.behavioral.strategy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 10:42
 */
public class PromotionActivity {

    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void executePromotionStrategy() {
        promotionStrategy.doPromotion();
    }
}
