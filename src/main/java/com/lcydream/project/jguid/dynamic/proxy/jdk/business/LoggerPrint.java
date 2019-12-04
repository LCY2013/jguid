package com.lcydream.project.jguid.dynamic.proxy.jdk.business;

/**
 * @ClassName LoggerPrint
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:02 下午
 **/
public class LoggerPrint implements LoggerInterface {

    @Override
    public <T> T fmt(T obj) {
        System.out.println(LoggerPrint.class);
        return obj;
    }
}
