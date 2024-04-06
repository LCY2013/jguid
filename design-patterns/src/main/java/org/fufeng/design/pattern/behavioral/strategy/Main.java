package org.fufeng.design.pattern.behavioral.strategy;

import org.springframework.beans.factory.support.InstantiationStrategy;

/**
 * 策略模式
 * {@link java.util.Comparator}
 * {@link InstantiationStrategy}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 10:42
 */
public class Main {

    //v1
    /*public static void main(String[] args) {
        PromotionActivity promotionActivity = new PromotionActivity(new FanxianPromotionStrategy());
        promotionActivity.executePromotionStrategy();
        promotionActivity = new PromotionActivity(new LiJianPromotionStrategy());
        promotionActivity.executePromotionStrategy();
        promotionActivity = new PromotionActivity(new ManJianPromotionStrategy());
        promotionActivity.executePromotionStrategy();
    }*/

    //v2
    /*public static void main(String[] args) {
        PromotionActivity promotionActivity = null;
        switch (args[0]) {
            case "LIJIAN":
                promotionActivity = new PromotionActivity(new LiJianPromotionStrategy());
                break;
            case "MANJIAN":
                promotionActivity = new PromotionActivity(new ManJianPromotionStrategy());
                break;
            case "FANXIAN":
                promotionActivity = new PromotionActivity(new FanxianPromotionStrategy());
                break;
            default:
                break;
        }
        assert promotionActivity != null;
        promotionActivity.executePromotionStrategy();
    }*/

    //v3
    public static void main(String[] args) {
        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(args[0]));
        promotionActivity.executePromotionStrategy();
    }

}
