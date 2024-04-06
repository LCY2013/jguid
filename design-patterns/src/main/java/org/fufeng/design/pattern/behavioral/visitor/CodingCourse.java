package org.fufeng.design.pattern.behavioral.visitor;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 19:16
 */
public class CodingCourse extends Course {
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
