/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-30
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.beans.properties;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @program: jguid
 * @description: {@link PropertyEditor} 属性编辑器
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-30
 * @see PropertyEditor
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -1213958669507030142L;

    private Long id;

    private String name;

    private int age;

    private Date updateTime;

    private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private final transient VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws PropertyVetoException {
        // 当name属性变化时,发送通知
        // 勉强属性(Constrained properties):当属性变化不合适时，阻断属性更新，并且通知异常来说明
        String propertyName = "name";

        // 老值
        String oldValue = this.name;
        // 修改后的新值
        String newValue = name;
        // 勉强属性（Constrained properties）必须在更新前执行
        // 校验规则：当名称为纯数字时，阻断更新
        // 当 PropertyVetoException 异常发生时
        fireVetoableChange(propertyName,oldValue,newValue);

        // this.name
        this.name = name;
        // 发布属性已经变化事件 - PropertyChangeEvent
        // 强迫属性（Bound Properties）：当属性变化时，强制更新并且发送通知属性变化通知事件
        firePropertyChange(propertyName,oldValue,newValue);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        // 勉强属性（Constrained properties）必须在更新前执行
        // 强迫属性（Bound Properties）：当属性变化时，强制更新并且发送通知属性变化通知事件
    }

    public void firePropertyChange(String propertyName,Object oldValue,Object newValue){
        // PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this,propertyName,oldValue,newValue);
        // 广播事件
        // 得到所有的PropertyChangeEvent监听器
        this.propertyChangeSupport.firePropertyChange(propertyName,oldValue,newValue);
    }

    public void fireVetoableChange(String propertyName,Object oldValue,Object newValue) throws PropertyVetoException {
        this.vetoableChangeSupport.fireVetoableChange(propertyName,oldValue,newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
        this.propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener){
        this.propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public PropertyChangeListener[] getPropertyChangeListeners() {
        return this.propertyChangeSupport.getPropertyChangeListeners();
    }

    public void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener){
        this.vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        this.vetoableChangeSupport.removeVetoableChangeListener(vetoableChangeListener);
    }

    public VetoableChangeListener[] getVetoableChangeListeners() {
        return this.vetoableChangeSupport.getVetoableChangeListeners();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 判断一段文本是否是数字
     *
     * @param context 文本
     * @return 是否是数字
     */
    public static boolean isNumeric(String context) {
        // 查看当前的文本内容是否为空
        if (Objects.isNull(context)) {
            return false;
        }
        // 逐个字符串判断是否是数字
        for (int i = 0; i < context.length(); i++) {
            if (!Character.isDigit(context.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
