package org.fufeng.design.pattern.behavioral.iterator;

/**
 * {@link java.util.Iterator}
 * {@link java.util.ArrayList.Itr}
 * {@link org.apache.ibatis.cursor.defaults.DefaultCursor}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 23:54
 */
public class Main {

    public static void main(String[] args) {
        Course course1 = new Course("Java");
        Course course2 = new Course("python");
        Course course3 = new Course("go");
        Course course4 = new Course("rust");
        Course course5 = new Course("c++");
        Course course6 = new Course("c");

        CourseAggregateImpl courseAggregate = new CourseAggregateImpl();
        courseAggregate.add(course1);
        courseAggregate.add(course2);
        courseAggregate.add(course3);
        courseAggregate.add(course4);
        courseAggregate.add(course5);
        courseAggregate.add(course6);
        System.out.println("-----课程列表-----");
        for (CourseIterator iterator = courseAggregate.getCourseIterator(); iterator.hasNext(); ) {
            Course course = iterator.next();
            System.out.println(course.getName());
        }

        System.out.println("-----删除课程-----");
        courseAggregate.remove(course3);
        for (CourseIterator iterator = courseAggregate.getCourseIterator(); iterator.hasNext(); ) {
            Course course = iterator.next();
            System.out.println(course.getName());
        }
    }

}
