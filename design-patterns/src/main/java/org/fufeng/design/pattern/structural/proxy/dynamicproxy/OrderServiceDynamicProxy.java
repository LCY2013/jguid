package org.fufeng.design.pattern.structural.proxy.dynamicproxy;

import org.fufeng.design.pattern.structural.proxy.Order;
import org.fufeng.design.pattern.structural.proxy.db.DataSourceContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * {@link org.springframework.aop.framework.ProxyFactoryBean}
 * {@link org.springframework.aop.framework.JdkDynamicAopProxy}
 * {@link org.springframework.aop.framework.CglibAopProxy}
 * {@link org.apache.ibatis.binding.MapperProxyFactory}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 22:59
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    private Object target;

    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    public Object bind() {
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before(Object obj) {
        System.out.println("动态代理 before 方法");
        int userId = 0;
        if (obj instanceof Order order) {
            userId = order.getUserId();
        }
        int dbRouter = userId % 2;
        System.out.println("动态代理分配到【db" + dbRouter + "】处理数据");
        // 选择不同的数据源
        DataSourceContextHolder.setDB(String.valueOf(dbRouter));
    }

    private void after() {
        System.out.println("动态代理 after method");
    }
}
