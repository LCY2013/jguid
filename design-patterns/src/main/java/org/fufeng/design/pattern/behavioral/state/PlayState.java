package org.fufeng.design.pattern.behavioral.state;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:36
 */
public class PlayState extends CourseVideoState {
    @Override
    public void play() {
        System.out.println("正常播放");
    }

    @Override
    public void speed() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.SPEED_STATE);
    }

    @Override
    public void pause() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.PAUSE_STATE);
    }

    @Override
    public void stop() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.STOP_STATE);
    }
}
