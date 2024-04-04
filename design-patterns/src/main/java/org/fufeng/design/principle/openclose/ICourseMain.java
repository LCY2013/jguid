package org.fufeng.design.principle.openclose;

/**
 * main
 *
 * @author fufeng
 * @Date 2024-04-04 10:35
 */
public class ICourseMain {

    public static void main(String[] args) {
        //ICourse javaCourse = new JavaCourse(1, "Java", 100D);
        ICourse iCourse = new JavaDiscountCourse(1, "Java", 100D);
        JavaDiscountCourse javaCourse = (JavaDiscountCourse) iCourse;
        System.out.println(javaCourse);
    }

}
