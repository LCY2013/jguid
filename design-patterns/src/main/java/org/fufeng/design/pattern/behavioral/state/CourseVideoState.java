package org.fufeng.design.pattern.behavioral.state;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:34
 */
public abstract class CourseVideoState {
    protected CourseVideoContext courseVideoContext;

    public void setCourseVideoContext(CourseVideoContext courseVideoContext) {
        this.courseVideoContext = courseVideoContext;
    }

    public abstract void play();
    public abstract void speed();
    public abstract void pause();
    public abstract void stop();

}
