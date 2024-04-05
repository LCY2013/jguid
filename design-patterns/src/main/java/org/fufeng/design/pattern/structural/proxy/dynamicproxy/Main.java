package org.fufeng.design.pattern.structural.proxy.dynamicproxy;

import org.fufeng.design.pattern.structural.proxy.IOrderService;
import org.fufeng.design.pattern.structural.proxy.Order;
import org.fufeng.design.pattern.structural.proxy.OrderServiceImpl;
import org.fufeng.design.pattern.structural.proxy.staticproxy.OrderServiceStaticProxy;

/**
 * 静态代理
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 22:43
 */
public class Main {

    public static void main(String[] args) {
        Order order = new Order();
        order.setUserId(1);
        //order.setUserId(2);

        IOrderService orderServiceDynamicProxy = (IOrderService)new OrderServiceDynamicProxy(new OrderServiceImpl()).bind();
        System.out.println(orderServiceDynamicProxy.createOrder(order));
    }

}