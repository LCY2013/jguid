package org.fufeng.design.pattern.behavioral.command;

/**
 * 命令模式
 * {@link Runnable}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 17:11
 */
public class Main {

    public static void main(String[] args) {
        CourseVideo courseVideo = new CourseVideo("Java从入门到精通");
        OpenCourseVideoCommand openCourseVideoCommand = new OpenCourseVideoCommand(courseVideo);
        CloseCourseVideoCommand closeCourseVideoCommand = new CloseCourseVideoCommand(courseVideo);
        Staff staff = new Staff();
        staff.addCommand(openCourseVideoCommand);
        staff.addCommand(closeCourseVideoCommand);
        staff.executeCommand();
    }

}
