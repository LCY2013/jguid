package org.fufeng.design.principle.singleresponsibility;

/**
 * 单一职责原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:05
 */
public class CourseImpl implements ICourseManager, ICourse {

    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }

    @Override
    public void studyCourse() {

    }

    @Override
    public void refundCourse() {

    }

}
