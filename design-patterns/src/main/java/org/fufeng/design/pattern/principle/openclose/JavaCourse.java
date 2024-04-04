package org.fufeng.design.pattern.principle.openclose;

/**
 * TODO
 *
 * @author fufeng
 * @Date 2024-04-04 10:33
 */
public class JavaCourse implements ICourse {

    private Integer id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "JavaCourse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
