package org.fufeng.design.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 23:46
 */
public class CourseAggregateImpl implements CourseAggregate {

    private List<Course> courses;

    public CourseAggregateImpl() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void add(Course course) {
        this.courses.add(course);
    }

    @Override
    public void remove(Course course) {
        this.courses.remove(course);
    }

    @Override
    public CourseIterator getCourseIterator() {
        return new CourseIteratorImpl(this.courses);
    }

}
