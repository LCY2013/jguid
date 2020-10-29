package com.lcydream.project.jguid.dynamic.proxy.jdk.aop;

/**
 * @ClassName LoggerAop
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:30 下午
 **/
public class LoggerAop implements Aop{

    @Override
    public void before(){
        System.out.println(fmtDate()+" 开始打印");
    }

    @Override
    public void after(){
        System.out.println(fmtDate()+" 结束打印");
    }

}
