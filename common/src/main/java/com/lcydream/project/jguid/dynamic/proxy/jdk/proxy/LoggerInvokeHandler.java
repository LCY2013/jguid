package com.lcydream.project.jguid.dynamic.proxy.jdk.proxy;

import com.lcydream.project.jguid.dynamic.proxy.jdk.aop.Aop;
import com.lcydream.project.jguid.dynamic.proxy.jdk.business.LoggerInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName LoggerInvokeHander
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:16 下午
 **/
public class LoggerInvokeHandler implements InvocationHandler {

    protected LoggerInterface target;

    private List<Aop> aops;

    public LoggerInvokeHandler(LoggerInterface loggerInterface) {
        this.target = loggerInterface;
    }

    public LoggerInvokeHandler(LoggerInterface loggerInterface,List<Aop> aops) {
        this.target = loggerInterface;
        this.aops = aops;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().equals("toString") || method.getName().equals("equals")) {
            return method.invoke(target, args);
        }

        //开始aop信息
        if(aops != null) {
            for (Aop aop : aops) {
                aop.before();
            }
        }

        Object obj=null;
        //执行JDK的方法
        obj = method.invoke(target, args);

        //结束aop信息
        if(aops != null) {
            for (Aop aop : aops) {
                aop.after();
            }
        }

        //返回结果集
        return obj;
    }
}
