package org.fufeng.design.pattern.structural.facade;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:56
 */
public class GiftExchangeService {

    //v1
    /*private QualifyService qualifierService;
    private PointsPaymentService pointsPaymentService;
    private ShippingService shippingService;

    public void setQualifierService(QualifyService qualifierService) {
        this.qualifierService = qualifierService;
    }

    public void setPointsPaymentService(PointsPaymentService pointsPaymentService) {
        this.pointsPaymentService = pointsPaymentService;
    }

    public void setShippingService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }*/

    //v2
    private QualifyService qualifierService = new QualifyService();
    private PointsPaymentService pointsPaymentService = new PointsPaymentService();
    private ShippingService shippingService = new ShippingService();

    public void giftExchange(PointsGift pointsGift) {
        if (qualifierService.isAvailable(pointsGift)) {
            //资格校验通过
            if (pointsPaymentService.pay(pointsGift)) {
                //如果支付成功
                String shippingOrderNo = shippingService.shipGift(pointsGift);
                System.out.println("物流系统下单成功,订单号: " + shippingOrderNo);
            }
        }
    }
}
