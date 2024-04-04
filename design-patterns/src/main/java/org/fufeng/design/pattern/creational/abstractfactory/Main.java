package org.fufeng.design.pattern.creational.abstractfactory;

/**
 * 抽象工厂模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 20:56
 */
public class Main {

    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }

}
