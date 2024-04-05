package org.fufeng.design.pattern.behavioral.iterator;

import java.util.List;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 23:50
 */
public class CourseIteratorImpl implements CourseIterator {
    private List<Course> courses;
    int position;
    Course course;
    public CourseIteratorImpl(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean hasNext() {
        return courses.size() > position;
    }

    @Override
    public Course next() {
        System.out.println("返回课程，位置是："+position);
        course = courses.get(position);
        position++;
        return course;
    }
}
