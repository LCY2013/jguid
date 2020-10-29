package com.lcydream.project.jguid.dynamic.proxy.custom.business;

/**
 * @ClassName CustomLoggerFmt
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/4 2:34 下午
 **/
public class CustomLoggerFmt implements CustomInterface {

    @Override
    public <T> void print(T t) {
        System.out.println("加油");
    }

    @Override
    public <T> T out(T t) {
        return t;
    }

    @Override
    public Object println(Object obj) {
        return obj;
    }

    @Override
    public void printF() {
        System.out.println("自定义实现");
    }
}
