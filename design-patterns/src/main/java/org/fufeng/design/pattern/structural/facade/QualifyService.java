package org.fufeng.design.pattern.structural.facade;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:53
 */
public class QualifyService {

    public boolean isAvailable(PointsGift pointsGift) {
        System.out.println("校验" + pointsGift.getName() + " 积分资格通过");
        return true;
    }
}
