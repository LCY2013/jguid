package org.fufeng.design.pattern.structural.facade;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:55
 */
public class ShippingService {

    public String shipGift(PointsGift pointsGift)
    {
        //物流系统的对接逻辑
        System.out.println(pointsGift.getName() + "进入物流系统");
        String shippingOrderNo = "666";
        return shippingOrderNo;
    }
}
