package com.lcydream.project.jguid.dynamic.proxy.jdk.proxy;

import com.lcydream.project.jguid.dynamic.proxy.jdk.business.LoggerInterface;

import java.lang.reflect.Proxy;

/**
 * @ClassName LoggerProxy
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:21 下午
 **/
public class LoggerProxy {

    public static LoggerInterface getLoggerInterface(LoggerInvokeHandler invokeHandler){
        return (LoggerInterface)Proxy.newProxyInstance(
                LoggerProxy.class.getClassLoader(),invokeHandler.target.getClass().getInterfaces(),invokeHandler);
    }

}
