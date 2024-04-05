package org.fufeng.design.pattern.structural.facade;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:55
 */
public class PointsPaymentService {

    public boolean pay(PointsGift pointsGift)
    {
        // 模拟积分支付
        System.out.println("支付" + pointsGift.getName() + " 积分成功");
        return true;
    }

}
