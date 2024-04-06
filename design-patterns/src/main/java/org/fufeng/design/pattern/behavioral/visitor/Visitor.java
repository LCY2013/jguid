package org.fufeng.design.pattern.behavioral.visitor;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:19
 */
public class Visitor implements IVisitor {

    // 免费课程
    @Override
    public void visit(FreeCourse course) {
        System.out.println("免费课程：" + course.getName());
    }

    // 实战课程
    @Override
    public void visit(CodingCourse course) {
        System.out.println("实战课程：" + course.getName() + "，价格：" + course.getPrice());
    }
}
