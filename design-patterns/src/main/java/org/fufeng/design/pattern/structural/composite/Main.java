package org.fufeng.design.pattern.structural.composite;

/**
 * 组合模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 21:07
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CatalogComponent linuxCourse = new Course("Linux课程", 11);
        CatalogComponent windowsCourse = new Course("Windows课程", 12);

        CatalogComponent javaCourse = new CourseCatalog("java", 2);
        CatalogComponent jcCourse = new Course("jc", 13);
        CatalogComponent jcCourse1 = new Course("jc1", 14);
        CatalogComponent jcCourse2 = new Course("jc2", 15);

        javaCourse.add(jcCourse);
        javaCourse.add(jcCourse1);
        javaCourse.add(jcCourse2);

        CatalogComponent mainCourse = new CourseCatalog("主课程", 2);
        mainCourse.add(linuxCourse);
        mainCourse.add(windowsCourse);
        mainCourse.add(javaCourse);
        mainCourse.print();
    }

}
