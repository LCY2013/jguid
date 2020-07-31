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
package org.fufeng.beans.introspection;

import org.fufeng.beans.properties.Person;

import java.beans.*;
import java.util.stream.Stream;

/**
 * @program: jguid
 * @description: {@link BeanDescriptor} Bean描述器
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-31
 * @see Introspector
 */
public class PersonIntrospectionInfo {

    public static void main(String[] args) throws IntrospectionException {
        // 获取Bean描述信息，排除java.lang.Object相关信息
        final BeanInfo beanInfo =
                Introspector.getBeanInfo(Person.class, Object.class);
        // 获取BeanDescriptor
        final BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println("Person 类的 Descriptor : "+beanDescriptor);
        System.out.println("=========================================");
        // 获取属性描述符PropertyDescriptor
        final PropertyDescriptor[] propertyDescriptors =
                beanInfo.getPropertyDescriptors();
        // 输出所有的属性描述符
        Stream.of(propertyDescriptors)
                .forEach(propertyDescriptor ->
                        System.out.println("Person 类的 PropertyDescriptor : "+propertyDescriptor));
        System.out.println("==========================================");
        // 获取Person类所有的方法描述符
        Stream.of(beanInfo.getMethodDescriptors())
                .forEach(methodDescriptor ->
                        System.out.println("Person 类的 MethodDescriptor : "+methodDescriptor));
        System.out.println("==========================================");
        // 获取Person类所有的事件描述符
        Stream.of(beanInfo.getEventSetDescriptors())
                .forEach(eventSetDescriptor ->
                        System.out.println("Person 类的 EventDescriptor : "+eventSetDescriptor));
    }

}
