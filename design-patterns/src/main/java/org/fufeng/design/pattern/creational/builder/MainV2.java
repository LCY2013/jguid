package org.fufeng.design.pattern.creational.builder;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * 建造者模式
 * {@link StringBuilder}
 * {@link StringBuffer}
 * {@link org.springframework.beans.factory.support.BeanDefinitionBuilder}
 * {@link SqlSessionFactoryBuilder}
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 21:57
 */
public class MainV2 {

    public static void main(String[] args) {
        CourseV2 courseV2 = new CourseV2.CourseBuilder().
                buildCourseName("java").
                buildCoursePPT("ppt").
                buildCourseQA("qa").
                buildCourseArticle("article").
                buildCourseVideo("video").build();
        System.out.println(courseV2);

        Set<String> set = ImmutableSet.<String>builder().add("a").add("b").build();
        System.out.println(set);
    }

}
