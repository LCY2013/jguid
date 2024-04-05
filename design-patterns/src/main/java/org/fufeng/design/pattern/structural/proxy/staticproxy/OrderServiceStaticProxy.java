package org.fufeng.design.pattern.structural.proxy.staticproxy;

import org.fufeng.design.pattern.structural.proxy.IOrderService;
import org.fufeng.design.pattern.structural.proxy.Order;
import org.fufeng.design.pattern.structural.proxy.OrderServiceImpl;
import org.fufeng.design.pattern.structural.proxy.db.DataSourceContextHolder;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:34
 */
public class OrderServiceStaticProxy {
    private IOrderService orderService;

    public int createOrder(Order order) {
        before(order);
        orderService = new OrderServiceImpl();
        int orderId = orderService.createOrder(order);
        after();
        return orderId;
    }

    private void before(Order order) {
        System.out.println("静态代理 before method");
        Integer userId = order.getUserId();
        int dbRouter = userId % 2;
        System.out.println("静态代理分配到【db" + dbRouter + "】处理数据");
        // 选择不同的数据源
        DataSourceContextHolder.setDB(String.valueOf(dbRouter));
    }

    private void after() {
        System.out.println("静态代理 after method");
    }
}
