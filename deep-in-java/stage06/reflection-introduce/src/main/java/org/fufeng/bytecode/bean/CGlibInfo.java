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

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.beans.*;
import java.lang.reflect.Method;

import static java.lang.String.format;
import static org.fufeng.bytecode.bean.Person.isNumeric;

/**
 * @program: jguid
 * @description: CGlib 代理类演示
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-04
 */
public class CGlibInfo {

    public static void main(String[] args) {
        // CGlib 代理Person
        Person person = (Person) newInstance(Person.class);
        // Person 添加PropertyChangeEvent监听器
        person.addPropertyChangeListener(evt -> {
            System.out.printf("属性[%s],发生改变,新值: [%s],老值: [%s]\n",evt.getPropertyName(),evt.getNewValue(),evt.getOldValue());
        });
        // Person 添加VetoChangeEvent监听器
        person.addVetoableChangeListener(evt -> {
            // 将新值转换成String类型
            final String newValue = String.valueOf(evt.getNewValue());
            // 判断是否是纯数字
            if (isNumeric(newValue)){
                throw new PropertyVetoException(format("属性[%s],新值[%s],不合法,不能为纯数字\n",evt.getPropertyName(),evt.getNewValue()),evt);
            }
        });
        // person 设置名称
        person.setName("fufeng");
        person.setName("magic");
        person.setName("hello");
        person.setName("lcy");
        person.setName("1226");
        System.out.println(person);
    }

    /**
     *  通过CGlib创建一个Person的子类代理
     * @param personClass Person字节码对象
     * @return 返回一个Person的代理子类
     */
    private static Object newInstance(Class<Person> personClass) {
        // 创建CGlib的核心
        final Enhancer enhancer = new Enhancer();
        // 创建一个方法拦截器
        MethodInterceptor methodInterceptor = new PublishingPropertyEventMethodInterceptor();
        // 设置需要被代理的父类信息
        enhancer.setSuperclass(Person.class);
        // 设置代理的拦截器
        enhancer.setCallback(methodInterceptor);
        // 创建代理出来的子类信息
        final Object object = enhancer.create();
        return object;
    }
}

class PublishingPropertyEventMethodInterceptor implements MethodInterceptor{

    // 属性改变的实现类
    private final transient PropertyChangeSupport propertyChangeSupport;
    // 是否需要更新属性改变的通知事件
    private final transient VetoableChangeSupport vetoableChangeSupport;

    /**
     *   构造函数初始化元素信息
     */
    public PublishingPropertyEventMethodInterceptor() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.vetoableChangeSupport = new VetoableChangeSupport(this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 获取代理类需要执行的方法 CGLib Class(新生成) extends Person
        final String methodName = method.getName();
        if ("addVetoAbleChangeListener".equals(methodName)){
            addVetoAbleChangeListener((VetoableChangeListener) objects[0]);
        }else if ("addPropertyChangeListener".equals(methodName)){
            addPropertyChangeListener((PropertyChangeListener) objects[0]);
        }else if ("removeVetoAbleChangeListener".equals(methodName)){
            removeVetoAbleChangeListener((VetoableChangeListener) objects[0]);
        }else if ("removePropertyChangeListener".equals(methodName)){
            removePropertyChangeListener((PropertyChangeListener) objects[0]);
        }else if ("getVetoAbleChangeListeners".equals(methodName)){
            return getVetoAbleChangeListeners();
        }else if ("getPropertyChangeListeners".equals(methodName)){
            return getPropertyChangeListeners();
        }else if ("setName".equals(methodName)   // 方法名称判断
                    && void.class.equals(method.getReturnType())  // 方法返回类型判断
                    && objects.length == 1    // 方法参数个数判断
                    && objects[0] instanceof String // 方法参数类型判断
        ){
            // 当name属性变化时发送通知
            // 勉强属性(constrained properties) : 当属性变化不合适时,阻止属性更新,并通过异常来说明
            String propertyName = "name";
            // 强转获取Person
            final Person person = (Person) o;
            // 读取老信息
            String oldName = person.getName();
            // 获取修改后的新值
            String newName = (String) objects[0];
            // 勉强属性(constrained properties) 必须在属性更新前执行
            // 校验规则: 当名字只有存数字的时候就阻断更新
            // 当PropertyVetoException异常发送时
            fireVetoAbleChange(propertyName,oldName,newName);

            // 调整person.name的值
            methodProxy.invokeSuper(o,objects);

            // 发布属性以及变化通知
            // 强迫属性(Bound Property):当属性发生变化,强制更新并发送通知属性变化通知事件
            firePropertyChange(propertyName,oldName,newName);
        }else {
            // 其他方法不用拦截,直接调用父类的方法即可
            return methodProxy.invokeSuper(o,objects);
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
