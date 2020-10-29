package com.lcydream.project.jguid.dynamic.proxy.jdk.aop;

/**
 * @ClassName TimeAop
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:40 下午
 **/
public class TimeAop implements Aop {

    @Override
    public void before() {
        System.out.println("开始执行时间:"+System.currentTimeMillis());
    }

    @Override
    public void after() {
        System.out.println("结束执行时间:"+System.currentTimeMillis());
    }

}
