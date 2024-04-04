package org.fufeng.design.pattern.creational.simplefactory;

import com.mysql.cj.jdbc.Driver;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * jdk 简单工厂示例
 * {@link Calendar}
 * {@link Calendar#createCalendar(TimeZone, Locale)}
 * {@link Driver)}
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:56
 */
public class VideoMain {

    public static void main(String[] args) {
        //v1
        //Video video = new JavaVideo();

        //v2
        /*VideoFactory videoFactory = new VideoFactory();
        Video video = videoFactory.getVideo("java");
        video.produce();*/

        //v2
        VideoFactory videoFactory = new VideoFactory();
        Video video = videoFactory.getVideo(JavaVideo.class);
        video.produce();
    }

}
