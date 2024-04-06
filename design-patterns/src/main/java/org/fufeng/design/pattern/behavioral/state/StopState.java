package org.fufeng.design.pattern.behavioral.state;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:36
 */
public class StopState extends  CourseVideoState {
    @Override
    public void play() {
        courseVideoContext.setCourseVideoStatus(CourseVideoContext.PLAY_STATE);
    }

    @Override
    public void speed() {
        System.out.println("ERROR: cannot speed in stop state");
    }

    @Override
    public void pause() {
        System.out.println("ERROR: cannot pause in stop state");
    }

    @Override
    public void stop() {
        System.out.println("stop video");
    }
}
