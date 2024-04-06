package org.fufeng.design.pattern.behavioral.visitor;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:15
 */
public class FreeCourse extends Course {

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
