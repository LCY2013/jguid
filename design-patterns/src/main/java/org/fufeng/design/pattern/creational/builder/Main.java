package org.fufeng.design.pattern.creational.builder;

/**
 * 建造者模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 21:48
 */
public class Main {

    public static void main(String[] args) {
        CourseBuilder courseBuilder = new CourseActualBuilder();
        Coach coach = new Coach();
        coach.setCourseBuilder(courseBuilder);
        coach.makeCourse("name",
                "PPT",
                "video",
                "article",
                "QA");
        System.out.println(coach);
    }

}
