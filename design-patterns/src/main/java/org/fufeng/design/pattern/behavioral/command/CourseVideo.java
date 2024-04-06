package org.fufeng.design.pattern.behavioral.command;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:12
 */
public class CourseVideo {
    private String name;

    public CourseVideo(String name) {
        this.name = name;
    }

    public void open() {
        System.out.println(this.name + "课程视频开放");
    }

    public void close() {
        System.out.println(this.name + "课程视频关闭");
    }
}
