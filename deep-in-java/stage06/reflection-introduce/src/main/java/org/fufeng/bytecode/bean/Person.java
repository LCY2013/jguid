/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-08-03
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

import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @program: jguid
 * @description: Person 人员领域
 * @author: <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @create: 2020-08-03
 */
public class Person implements Nameable, Serializable {

    private static final long serialVersionUID = 5663167364698750497L;

    private Long id;

    private String name;

    private int age;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
        // 勉强属性(Constrained properties): 必须在更新前执行
        // 强迫属性(Bound properties): 当属性发送变化时，强制更新并发送属性变化通知事件
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
     *  判断一串字符是不是数字字符串
     * @param context 待判断的字符内容
     * @return 是不是数字字符串
     */
    public static boolean isNumeric(String context){
        if (Objects.isNull(context)){
            return false;
        }
        for (int i=0;i<context.length();i++){
            if (!Character.isDigit(context.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
    }

}
