package com.lcydream.project.jguid.dynamic.proxy.jdk.business;

/**
 * @ClassName LoggerFmt
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:03 下午
 **/
public class LoggerFmt implements LoggerInterface {

    @Override
    public <T> T fmt(T obj) {
        System.out.println(LoggerFmt.class);
        return obj;
    }
}
