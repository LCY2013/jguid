package org.fufeng.design.pattern.behavioral.templatemethod;

/**
 * {@link java.util.AbstractList}
 * {@link java.util.AbstractSet}
 * {@link java.util.AbstractMap}
 * {@link java.util.AbstractMap}
 * {@link jakarta.servlet.http.HttpServlet}
 * {@link org.apache.ibatis.executor.BaseExecutor}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 23:31
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("---Java课程 start---");
        ACourse javaCourse = new DesignPatternCourse();
        javaCourse.makeCourse();
        System.out.println("---Java课程 end---");

        System.out.println("---Python课程 start---");
        ACourse pythonCourse = new PythonCourse();
        pythonCourse.makeCourse();
        System.out.println("---Python课程 end---");

        System.out.println("---FE课程 start---");
        ACourse feCourse = new FECourse(true);
        feCourse.makeCourse();
        System.out.println("---FE课程 end---");
    }

}
