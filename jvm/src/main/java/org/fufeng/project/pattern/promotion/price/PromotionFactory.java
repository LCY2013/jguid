/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-25
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.pattern.promotion.price;

import org.fufeng.project.pattern.promotion.count.BaseCount;
import org.fufeng.project.pattern.promotion.count.IBaseCount;
import org.fufeng.project.pattern.promotion.decorator.CouponDecorator;
import org.fufeng.project.pattern.promotion.decorator.RedPacketDecorator;
import org.fufeng.project.pattern.promotion.order.OrderDetail;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 计算促销后的价格
 * @create 2020-12-25
 */
public class PromotionFactory {

    /**
     * 计算促销后的价格
     *
     * @param orderDetail 订单明细
     * @return 促销后的价格
     */
    public static BigDecimal getPayMoney(OrderDetail orderDetail) {
        // 获取商品支持的促销类型
        final Map<PromotionType, SupportPromotions> supportPromotions = orderDetail
                .getMerchandise()
                .getSupportPromotions();
        // 初始化计算类
        IBaseCount baseCount = new BaseCount();
        if (Objects.nonNull(supportPromotions) && supportPromotions.size() > 0) {
            // 遍历设置的促销类型，通过装饰器组合促销类型
            for (PromotionType promotionType : supportPromotions.keySet()) {
                baseCount = promotion(supportPromotions.get(promotionType), baseCount);
            }
        }
        // 计算最后促销后的价格
        return baseCount.countPayMoney(orderDetail);
    }

    /**
     * 获取促销类型计算工具
     *
     * @param supportPromotions 支持的优惠类型
     * @param baseCount         计算抽象
     * @return 具体的优惠计算实现
     */
    private static IBaseCount promotion(SupportPromotions supportPromotions,
                                        IBaseCount baseCount) {
        if (supportPromotions.getPromotionType() == PromotionType.COUPON) {
            baseCount = new CouponDecorator(baseCount);
        } else if (supportPromotions.getPromotionType() == PromotionType.READ_PACKED) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }

}
