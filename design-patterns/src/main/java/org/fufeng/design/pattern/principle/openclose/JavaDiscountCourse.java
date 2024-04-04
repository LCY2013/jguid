package org.fufeng.design.pattern.principle.openclose;

/**
 * 里氏替换原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 11:06
 */
public class JavaDiscountCourse extends JavaCourse {

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginalPrice() {
        return super.getPrice();
    }

    @Override
    public Double getPrice() {
        return super.getPrice() * 0.8;
    }

    @Override
    public String toString() {
        return "JavaCourse{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
