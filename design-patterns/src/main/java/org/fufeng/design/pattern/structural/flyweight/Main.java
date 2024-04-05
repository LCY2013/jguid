package org.fufeng.design.pattern.structural.flyweight;

/**
 * 享元模式
 * {@link Integer}
 * {@link Long}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 20:27
 */
public class Main {

    private static final String[] departments = {
            "RD", "QA", "PM", "BD"
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String department = departments[(int) (Math.random() * departments.length)];
            Employee e = EmployeeFactory.getEmployee(department);
            e.report();
        }

        System.out.println("======================================");
        // java.lang.Integer.IntegerCache.high
        Integer a = Integer.valueOf(100);
        Integer b = 100;
        System.out.println(a==b);

        Integer c = Integer.valueOf(300);
        Integer d = 300;
        System.out.println(c==d);
    }

}
