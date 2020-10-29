package com.lcydream.project.jguid.dynamic.proxy.custom.business;

/**
 * @ClassName CustomInterface
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/4 10:54 上午
 **/
public interface CustomInterface {

   <T> void print(T t);

   <T> T out(T t);

   Object println(Object obj);

   void printF();

}
