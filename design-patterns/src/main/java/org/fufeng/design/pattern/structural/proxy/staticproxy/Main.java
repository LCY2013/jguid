package org.fufeng.design.pattern.structural.proxy.staticproxy;

import org.fufeng.design.pattern.structural.proxy.Order;

/**
 * 静态代理
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 22:43
 */
public class Main {

    public static void main(String[] args) {
        Order order = new Order();
        //order.setUserId(1);
        order.setUserId(2);

        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy();
        System.out.println(orderServiceStaticProxy.createOrder(order));
    }

}