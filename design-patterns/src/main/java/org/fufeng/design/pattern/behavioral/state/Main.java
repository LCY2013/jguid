package org.fufeng.design.pattern.behavioral.state;

/**
 * 状态模式
 * @author fufeng
 * {@code @Date} 2024-04-06 19:45
 */
public class Main {

    public static void main(String[] args) {
        CourseVideoContext courseVideoContext = new CourseVideoContext();
        courseVideoContext.setCourseVideoStatus(new PlayState());

        System.out.println("当前状态：" + courseVideoContext.getCourseVideoStatus().getClass().getSimpleName());
        courseVideoContext.pause();

        System.out.println("当前状态：" + courseVideoContext.getCourseVideoStatus().getClass().getSimpleName());
        courseVideoContext.speed();

        System.out.println("当前状态：" + courseVideoContext.getCourseVideoStatus().getClass().getSimpleName());
        courseVideoContext.stop();

        System.out.println("当前状态：" + courseVideoContext.getCourseVideoStatus().getClass().getSimpleName());
        courseVideoContext.speed();
    }

}
