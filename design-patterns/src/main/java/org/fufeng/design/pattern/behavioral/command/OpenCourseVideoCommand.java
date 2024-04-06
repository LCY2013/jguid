package org.fufeng.design.pattern.behavioral.command;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:13
 */
public class OpenCourseVideoCommand implements Command{

    private CourseVideo courseVideo;

    public OpenCourseVideoCommand(CourseVideo courseVideo) {
        this.courseVideo = courseVideo;
    }

    @Override
    public void execute() {
        courseVideo.open();
    }

}
