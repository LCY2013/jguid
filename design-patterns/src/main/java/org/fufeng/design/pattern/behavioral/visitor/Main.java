package org.fufeng.design.pattern.behavioral.visitor;


import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 * {@link java.nio.file.FileVisitor}
 * {@link org.springframework.beans.factory.config.BeanDefinitionVisitor}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 19:24
 */
public class Main {

    public static void main(String[] args) {
        List<Course> courseList = new ArrayList<>();

        FreeCourse freeCourse = new FreeCourse();
        freeCourse.setName("免费课程");

        CodingCourse codingCourse = new CodingCourse();
        codingCourse.setName("实战课程");
        codingCourse.setPrice(100);

        courseList.add(freeCourse);
        courseList.add(codingCourse);

        for (Course course : courseList) {
            course.accept(new Visitor());
        }
    }

}
