package org.fufeng.design.pattern.structural.proxy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:31
 */
public class OrderServiceImpl implements IOrderService {

    private IOrderDao iOrderDao;

    @Override
    public int createOrder(Order order) {
        // 模拟创建订单, 可以下面应该依赖DI
        iOrderDao = new OrderDaoImpl();

        System.out.println("order service invoke order dao");

        return iOrderDao.insert(order);
    }

}
