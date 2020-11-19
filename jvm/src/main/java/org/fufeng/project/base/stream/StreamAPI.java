/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-19
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.stream;

import org.fufeng.project.base.stream.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description 演示Stream相关API
 * @create 2020-11-19
 * @see Stream
 */
public class StreamAPI {

    /**
     * 通过传统的iterator 过滤分组一所中学里身高在 160cm 以上的男女同学
     */
    private static void iterator01(List<Student> studentList) {
        final Map<String, List<Student>> stuMap = new HashMap<>();
        for (Student student : studentList) {
            if (student.getHeight() > 160) {
                if (stuMap.get(student.getSex()) == null) {
                    // 创建不同分组的学生集合
                    final List<Student> stuList = new ArrayList<>();

                    stuList.add(student);

                    stuMap.put(student.getSex(), stuList);
                } else { // 性别分组已经存在
                    stuMap.get(student.getSex()).add(student); // 直接将该学生直接添加到该性别分组
                }
            }
        }
        System.out.println("iterator01");
        stuMap.forEach((k, v) ->
                System.out.printf("%s - %s\n", k, v));
    }

    /**
     * 通过Stream 串行 过滤分组一所中学里身高在 160cm 以上的男女同学
     */
    private static void stream01(List<Student> studentList) {
        final Map<String, List<Student>> groupMap = studentList.stream()
                .filter(student -> student.getHeight() > 160)
                .collect(Collectors.groupingBy(Student::getSex));
        System.out.println("stream01");
        groupMap.forEach((k, v) ->
                System.out.printf("%s - %s\n", k, v));
    }

    /**
     * 通过Stream 并行 过滤分组一所中学里身高在 160cm 以上的男女同学
     */
    private static void stream02(List<Student> studentList) {
        final Map<String, List<Student>> groupMap = studentList.parallelStream()
                .filter(student -> student.getHeight() > 160)
                .collect(Collectors.groupingBy(Student::getSex));
        System.out.println("stream02");
        groupMap.forEach((k, v) ->
                System.out.printf("%s - %s\n", k, v));
    }

    public static void main(String[] args) {
        System.out.println("示例一");
        final List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("1","男",158));
        studentList.add(new Student("2","女",178));
        studentList.add(new Student("3","男",188));
        studentList.add(new Student("4","女",168));
        studentList.add(new Student("5","男",175));
        iterator01(studentList);
        stream01(studentList);
        stream02(studentList);


    }

}
