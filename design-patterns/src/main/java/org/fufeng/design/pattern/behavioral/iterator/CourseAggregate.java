package org.fufeng.design.pattern.behavioral.iterator;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 23:45
 */
public interface CourseAggregate {

    void add(Course course);
    void remove(Course course);
    CourseIterator getCourseIterator();
}
