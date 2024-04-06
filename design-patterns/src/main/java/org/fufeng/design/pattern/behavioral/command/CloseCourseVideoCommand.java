package org.fufeng.design.pattern.behavioral.command;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:14
 */
public class CloseCourseVideoCommand implements Command {

    private CourseVideo courseVideo;

    public CloseCourseVideoCommand(CourseVideo courseVideo) {
        this.courseVideo = courseVideo;
    }

    @Override
    public void execute() {
        courseVideo.close();
    }
}
