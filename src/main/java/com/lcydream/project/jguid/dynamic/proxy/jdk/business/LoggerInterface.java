package com.lcydream.project.jguid.dynamic.proxy.jdk.business;

/**
 * @ClassName LoggerInterface
 * @Description 定义日志规范
 * @Author fufeng1226
 * @Date 2019/12/3 4:00 下午
 **/
public interface LoggerInterface {

    <T> T fmt(T obj);

}
