package org.fufeng.design.pattern.creational.abstractfactory;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 20:21
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }

}
