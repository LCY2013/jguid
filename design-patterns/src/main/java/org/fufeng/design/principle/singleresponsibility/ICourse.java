package org.fufeng.design.principle.singleresponsibility;

/**
 * 单一职责原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:02
 */
public interface ICourse {

    String getCourseName();
    byte[] getCourseVideo();

    // 拆开管理接口
    /*void studyCourse();
    void refundCourse();*/

}
