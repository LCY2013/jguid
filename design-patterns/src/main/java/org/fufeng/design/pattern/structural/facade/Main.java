package org.fufeng.design.pattern.structural.facade;

/**
 * 外观模式（门面模式）
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:59
 */
public class Main {

    public static void main(String[] args) {
        PointsGift pointsGift = new PointsGift("T恤");
        GiftExchangeService giftExchangeService = new GiftExchangeService();
        /*giftExchangeService.setQualifierService(new QualifyService());
        giftExchangeService.setPointsPaymentService(new PointsPaymentService());
        giftExchangeService.setShippingService(new ShippingService());*/
        giftExchangeService.giftExchange(pointsGift);

    }

}
