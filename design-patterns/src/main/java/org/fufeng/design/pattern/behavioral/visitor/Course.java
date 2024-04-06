package org.fufeng.design.pattern.behavioral.visitor;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:15
 */
public abstract class Course {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void accept(IVisitor visitor);

}
