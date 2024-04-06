package org.fufeng.design.pattern.behavioral.visitor;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:15
 */
public interface IVisitor {

    void visit(FreeCourse course);

    void visit(CodingCourse course);

}
