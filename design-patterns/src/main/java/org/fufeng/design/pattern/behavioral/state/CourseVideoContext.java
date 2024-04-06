package org.fufeng.design.pattern.behavioral.state;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:35
 */
public class CourseVideoContext {
    private CourseVideoState courseVideoStatus;
    protected static final PlayState PLAY_STATE = new PlayState();
    protected static final StopState STOP_STATE = new StopState();
    protected static final SpeedState SPEED_STATE = new SpeedState();
    protected static final PauseState PAUSE_STATE = new PauseState();

    public CourseVideoState getCourseVideoStatus() {
        return courseVideoStatus;
    }

    public void setCourseVideoStatus(CourseVideoState courseVideoStatus) {
        this.courseVideoStatus = courseVideoStatus;
        this.courseVideoStatus.setCourseVideoContext(this);
    }

    public void play() {
        this.courseVideoStatus.play();
    }

    public void speed() {
        this.courseVideoStatus.speed();
    }

    public void stop() {
        this.courseVideoStatus.stop();
    }

    public void pause() {
        this.courseVideoStatus.pause();
    }
}
