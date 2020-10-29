package com.lcydream.project.jguid.dynamic.proxy.cglib;

import com.lcydream.project.jguid.dynamic.proxy.cglib.business.LoggerCglibFmt;
import com.lcydream.project.jguid.dynamic.proxy.cglib.proxy.LoggerCglibProxy;

/**
 * @ClassName CglibTest
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 5:17 下午
 **/
public class CglibTest {

    public static void main(String[] args) {
        final LoggerCglibFmt loggerCglibFmt = LoggerCglibProxy.getLoggerCglibFmt();
        loggerCglibFmt.logger();
    }

}
