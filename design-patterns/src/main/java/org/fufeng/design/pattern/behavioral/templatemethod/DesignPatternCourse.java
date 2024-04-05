package org.fufeng.design.pattern.behavioral.templatemethod;

/**
 * @author fufeng
 * {@code @Date} 2024-04-05 23:29
 */
public class DesignPatternCourse extends ACourse {

    @Override
    void packageCourse() {
        System.out.println("提供Java课程源代码");
    }

    @Override
    protected boolean needWriteArticle() {
        return true;
    }

}
