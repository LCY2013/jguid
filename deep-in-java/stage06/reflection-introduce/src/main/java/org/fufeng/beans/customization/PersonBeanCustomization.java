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
package org.fufeng.beans.customization;

import org.fufeng.beans.properties.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditor;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link BeanInfo} Bean相关信息
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-31
 * @see BeanInfo
 * @see PropertyEditor
 */
public class PersonBeanCustomization {

    public static void main(String[] args) throws IntrospectionException {
        // 获取Person类型,排除java.lang.Object对普通类型的影响
        final BeanInfo beanInfo =
                Introspector.getBeanInfo(Person.class, Object.class);
        // 创建一个Person Bean实例
        Person personBean = new Person();

        // 给Person的id属性设置属性编辑器
        Stream.of(beanInfo.getPropertyDescriptors())
                .filter(propertyDescriptor ->
                        "id".equals(propertyDescriptor.getName())) //筛选出属性为id的字段
                .findFirst()
                .ifPresent(propertyDescriptor -> {
                    // 为 属性 叫 id 的注册属性修改器
                    propertyDescriptor.setPropertyEditorClass(IdPropertyEditor.class);
                    // 为personBean 创建一个属性编辑器
                    final PropertyEditor propertyEditor =
                            propertyDescriptor.createPropertyEditor(personBean);
                    // 添加属性变化事件
                    propertyEditor.addPropertyChangeListener(event ->
                            personBean.setId((Long) propertyEditor.getValue())
                    );
                    // 通过模拟spring
                    // <bean class="Person">
                    // <property name="id">18</property>
                    // </bean>
                    propertyEditor.setAsText("18");
                });

        // 给Person属性为updateTime属性设置属性编辑器
        Stream.of(beanInfo.getPropertyDescriptors())
                .filter(propertyDescriptor ->
                        "updateTime".equals(propertyDescriptor.getName())) // 过滤属性名称为updateTime的属性
                .findFirst()
                .ifPresent(propertyDescriptor -> {
                    // 首先给属性字段updateTime设置一个属性编辑器
                    propertyDescriptor.setPropertyEditorClass(UpdateTimePropertyEditor.class);
                    // 创建一个Person的属性编辑器
                    final PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(personBean);
                    // 给属性编辑器添加一个属性变化监听器
                    propertyEditor.addPropertyChangeListener(event -> personBean.setUpdateTime((Date)propertyEditor.getValue()));

                    // 模拟<property name="updateTime">2020-07-31 11:00:00</property>
                    propertyEditor.setAsText("2020-07-31 11:00:00");
                });

        System.out.println("person = " + personBean);
    }

}
