package com.lcydream.project.jguid.dynamic.proxy.jdk.aop;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName Aop
 * @Description 定义aop内容，这里可以添加类似spring order的排序处理
 * @Author fufeng1226
 * @Date 2019/12/3 4:35 下午
 **/
public interface Aop {

    void before();

    void after();

    default String fmtDate(){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(new Date())+":";
    }
}
