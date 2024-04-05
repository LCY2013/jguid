package org.fufeng.design.pattern.structural.proxy;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 22:30
 */
public class OrderDaoImpl implements IOrderDao {

    @Override
    public int insert(Order order) {
        System.out.println("dao insert order success");
        return 0;
    }

}
