package com.lcydream.project.jguid.dynamic.proxy.cglib.proxy;

import com.lcydream.project.jguid.dynamic.proxy.cglib.business.LoggerCglibFmt;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName LoggerCglibProxy
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 5:11 下午
 **/
public class LoggerCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始");
        final Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("结束");
        return invoke;
    }

    public static LoggerCglibFmt getLoggerCglibFmt(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LoggerCglibFmt.class);
        enhancer.setCallback(new LoggerCglibProxy());
        LoggerCglibFmt loggerCglibFmt = (LoggerCglibFmt)enhancer.create();
        return loggerCglibFmt;
    }
}
