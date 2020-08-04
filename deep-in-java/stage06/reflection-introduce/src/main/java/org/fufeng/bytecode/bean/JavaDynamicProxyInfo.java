/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-04
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.bytecode.bean;

import java.beans.*;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.EventListener;

import static java.lang.String.format;
import static org.fufeng.bytecode.bean.Person.isNumeric;

/**
 * @program: jguid
 * @description: {@link Proxy} jdk动态代理
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 * @see Proxy
 */
public class JavaDynamicProxyInfo {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        // Java 动态代理
        // 动态代理的对象是来自于java.lang.reflect.Proxy的子类(实例)
        // jdk动态代理的类继承了proxy接口，也实现了期望的多个接口列表
        // 缺点: 只能拦截接口，不能拦截类，对编程不友好
        Nameable nameable = newInstance(person);
        nameable.setName("fufeng");
        nameable.setName("magic");
        nameable.setName("lcy");
        nameable.setName("123");
        nameable.setName("fufeng");
    }

    /**
     *  创建一个基于Person的代理类
     * @param person 需要代理的对象实现
     * @return 返回一个父类接口
     */
    private static Nameable newInstance(Person person) {
        // 获取当前类的ClassLoader
        final ClassLoader classLoader = JavaDynamicProxyInfo.class.getClassLoader();
        // 创建一个发布属性变化事件的实现拦截器
        final PublishingPropertyEventInvocationHandler invocationHandler = new PublishingPropertyEventInvocationHandler(person);
        // 添加PropertyChangeEvent监听器
        invocationHandler.addPropertyChangeListener(evt -> {
            // 属性变化通知事件
            System.out.printf("监听到属性[%s]内容变化(事件来源: %s),老值: %s,新值: %s\n",
                        evt.getPropertyName(),
                        evt.getSource(),
                        evt.getOldValue(),
                        evt.getNewValue());
        });
        // 添加VetoChangeEvent监听器
        invocationHandler.addVetoAbleChangeListener(evt -> {
            // 获取新值
            String newValue = String.valueOf(evt.getNewValue());
            // 属性变化前通知事件
            if (isNumeric(newValue)){
                throw new PropertyVetoException(format("当前属性[%s],新值[%s]不合法,不能为数字!",evt.getPropertyName(),newValue),evt);
            }
        });

        return (Nameable)Proxy.newProxyInstance(classLoader,new Class[]{Nameable.class, Serializable.class, EventListener.class},invocationHandler);
    }
}

/**
 *  创建一个拦截处理器的实现
 */
class PublishingPropertyEventInvocationHandler implements InvocationHandler{

    // 持有Person实例
    private final Person person;
    // 属性改变的实现类
    private final transient PropertyChangeSupport propertyChangeSupport;
    // 是否需要更新属性改变的通知事件
    private final transient VetoableChangeSupport vetoableChangeSupport;

    /**
     *   构造函数初始化元素信息
     * @param person Person对象信息
     */
    public PublishingPropertyEventInvocationHandler(Person person) {
        this.person = person;
        this.propertyChangeSupport = new PropertyChangeSupport(person);
        this.vetoableChangeSupport = new VetoableChangeSupport(person);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取当前对象实例的执行方法
        final String methodName = method.getName();
        if ("setName".equals(methodName) &&  // 方法名称判断
                void.class.equals(method.getReturnType()) &&  // 方法返回值判断
                args.length == 1 &&   // 参数个数判断
                args[0] instanceof String  // 参数类型判断
            ){
            // 当name属性变化时发送通知
            // 勉强属性(constrained properties) : 当属性变化不合适时,阻止属性更新,并通过异常来说明
            String propertyName = "name";
            // 读取老信息
            String oldName = this.person.getName();
            // 获取修改后的新值
            String newName = (String) args[0];
            // 勉强属性(constrained properties) 必须在属性更新前执行
            // 校验规则: 当名字只有存数字的时候就阻断更新
            // 当PropertyVetoException异常发送时
            fireVetoAbleChange(propertyName,oldName,newName);
            // 调整person.name的值
            this.person.setName(newName);
            // 发布属性以及变化通知
            // 强迫属性(Bound Property):当属性发生变化,强制更新并发送通知属性变化通知事件
            firePropertyChange(propertyName,oldName,newName);
        }
        return null;
    }

    /**
     *   属性更新后强制发送一个属性改变事件
     * @param propertyName 属性名称
     * @param oldName 老值
     * @param newName 新值
     */
    private void firePropertyChange(String propertyName, String oldName, String newName){
        // final PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, propertyName, oldName, newName);
        // 广播事件
        // 得到所有的PropertyChangeEvent 监听器
        this.propertyChangeSupport.firePropertyChange(propertyName,oldName,newName);
    }

    /**
     *  属性修改后发生属性更新前执行校验
     * @param propertyName 属性名称
     * @param oldName 老值
     * @param newName 新值
     */
    private void fireVetoAbleChange(String propertyName, String oldName, String newName) throws PropertyVetoException {
        this.vetoableChangeSupport.fireVetoableChange(propertyName,oldName,newName);
    }

    public void addVetoAbleChangeListener(VetoableChangeListener vetoableChangeListener){
        this.vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    public void removeVetoAbleChangeListener(VetoableChangeListener vetoableChangeListener){
        this.vetoableChangeSupport.removeVetoableChangeListener(vetoableChangeListener);
    }

    public VetoableChangeListener[] getVetoAbleChangeListeners(){
        return this.vetoableChangeSupport.getVetoableChangeListeners();
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
        this.propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener){
        this.propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners(){
        return this.propertyChangeSupport.getPropertyChangeListeners();
    }

}
