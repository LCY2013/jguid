package com.lcydream.project.jguid.dynamic.proxy.custom.proxy;

import java.lang.reflect.Method;

/**
 * @ClassName CustomInvokeHandler
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 5:29 下午
 **/
public interface CustomInvokeHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
