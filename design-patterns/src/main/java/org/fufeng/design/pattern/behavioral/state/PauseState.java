package org.fufeng.design.pattern.behavioral.state;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:36
 */
public class PauseState extends CourseVideoState {

    @Override
    public void play() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.SPEED_STATE);
    }

    @Override
    public void pause() {
        System.out.println("暂停播放");
    }

    @Override
    public void stop() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.STOP_STATE);
    }
}
