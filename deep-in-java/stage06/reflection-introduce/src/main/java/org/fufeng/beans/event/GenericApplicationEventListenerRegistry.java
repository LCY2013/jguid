/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-31
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.beans.event;

import net.jodah.typetools.TypeResolver;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

/**
 * @program: jguid
 * @description: 支持泛型的 {@link ApplicationEventListenerRegistry} 事件监听器注册器
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-31
 * @see ApplicationEventListenerRegistry 事件监听器注册容器实现
 * @see ApplicationEventListener 事件监听器
 */
public class GenericApplicationEventListenerRegistry implements ApplicationEventListenerRegistry{

    /**
     *  监听器注册容器
     *  key -> 监听器的类型名称
     *  value -> 相同类型的监听器集合
     */
    private Map<String, List<ApplicationEventListener<?>>> eventListeners =
            new LinkedHashMap<>();

    @Override
    public void addApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        // 获取监听器容器
        final List<ApplicationEventListener<?>> listeners = getListeners(applicationEventListener);
        // 添加事件监听器到容器
        listeners.add(applicationEventListener);
    }

    @Override
    public void removeApplicationEventListener(ApplicationEventListener<?> applicationEventListener) {
        // 获取监听器容器
        final List<ApplicationEventListener<?>> listeners = getListeners(applicationEventListener);
        listeners.remove(applicationEventListener);
    }

    /**
     * 返回一个只读的{@link ApplicationEventListener} 集合
     * @return 返回所有的事件监听器
     */
    @Override
    public ApplicationEventListener<?>[] getApplicationEventListeners() {
        return this.eventListeners.values().stream()
                .reduce((beforeListeners,afterListeners) -> {
                    beforeListeners.addAll(afterListeners);
                    return beforeListeners;
                }).orElse(emptyList())
                .toArray(new ApplicationEventListener<?>[0]);
        //return new ApplicationEventListener[0];
    }

    public static void main(String[] args) {
        HashMap<String,List<String>> map = new HashMap<>();
        List<String> list1 = new LinkedList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        map.put("1",list1);
        List<String> list2 = new LinkedList<>();
        list2.add("d");
        list2.add("e");
        list2.add("f");
        map.put("2",list2);
        List<String> list3 = new LinkedList<>();
        list3.add("g");
        list3.add("h");
        list3.add("j");
        map.put("3",list3);
        String[] strings = map.values().stream()
                .reduce((b,f)->{
                    b.addAll(f);
                    return b;
                }).orElse(emptyList())
                .toArray(new String[0]);
        for (String str : strings){
            System.out.println(str);
        }
    }

    @Override
    public ApplicationEventListener<?>[] getApplicationEventListeners(Class<? extends ApplicationEvent> eventType) {
        // 获取所有的ApplicationEvent事件监听器
        final List<ApplicationEventListener<?>> applicationEventListeners =
                this.eventListeners.getOrDefault(ApplicationEvent.class.getTypeName(), emptyList());
        if (eventType.equals(ApplicationEvent.class)){
            return applicationEventListeners.toArray(new ApplicationEventListener[0]);
        }
        final String typeName = eventType.getTypeName();
        // 获取某种类型的事件
        final List<ApplicationEventListener<?>> applicationEventListenerList =
                this.eventListeners.getOrDefault(typeName, emptyList());
        applicationEventListenerList.addAll(applicationEventListeners);
        return applicationEventListenerList.toArray(new ApplicationEventListener<?>[0]);
    }

    /**
     *  通过传入的ApplicationEventListener获取存在的容器集合
     * @param applicationEventListener 事件监听器
     * @return 事件监听器集合
     */
    protected List<ApplicationEventListener<? extends ApplicationEvent>> getListeners(ApplicationEventListener<?> applicationEventListener){
        // 获取事件监听器的类实例
        final Class<? extends ApplicationEventListener> eventListenerClass =
                applicationEventListener.getClass();
        // 获取事件监听器的上层泛型类型
        final Type[] eventListenerClassGenericInterfaces =
                eventListenerClass.getGenericInterfaces();
        // 通过流式操作获取泛型中的事件类型
        String eventTypeName = Stream.of(eventListenerClassGenericInterfaces)
                // 筛选出类型是ParameterizedType的type
                .filter(type -> type instanceof ParameterizedType)
                // 转换Type -> ParameterizedType
                .map(type -> (ParameterizedType) type)
                // 判断接口是否是原始类型
                .filter(parameterizedType -> ApplicationEventListener.class.equals(parameterizedType.getRawType()))
                // 操作获取泛型类型
                .map(parameterizedType -> {
                    // 获取第一个泛型参数
                    return parameterizedType.getActualTypeArguments()[0].getTypeName();
                }).findFirst().orElse(null);
        // 如果获取的参数类型是空,如果是空则说明它来自匿名函数或者lambda表达式
        if (Objects.isNull(eventTypeName)){
            final Class<?> lambdaClass = TypeResolver.resolveRawArgument(ApplicationEventListener.class, eventListenerClass);
            if (Objects.nonNull(lambdaClass)){
                eventTypeName = lambdaClass.getTypeName();
            }
        }
        return this.eventListeners.computeIfAbsent(eventTypeName,k -> new LinkedList<>());
    }

    /**
     *  对于{@link ApplicationEventListener} 的比较器，用与排序去重
     */
    static class ApplicationEventListenerComparator
            implements Comparator<ApplicationEventListener<?>>{
        @Override
        public int compare(ApplicationEventListener<?> o1, ApplicationEventListener<?> o2) {
            final String o1Name = o1.getClass().getName();
            final String o2Name = o2.getClass().getName();
            return o1Name.compareTo(o2Name);
        }
    }
}
