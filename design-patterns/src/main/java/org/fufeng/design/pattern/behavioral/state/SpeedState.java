package org.fufeng.design.pattern.behavioral.state;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:36
 */
public class SpeedState extends CourseVideoState {

    @Override
    public void play() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("快进播放");
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
