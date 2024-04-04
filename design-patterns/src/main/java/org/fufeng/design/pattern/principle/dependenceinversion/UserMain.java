package org.fufeng.design.pattern.principle.dependenceinversion;

/**
 * 依赖倒置
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 15:58
 */
public class UserMain {

    /*v1
    public static void main(String[] args) {
        // 依赖了具体实现，不符合依赖倒置原则
        User user = new User();
        user.studyJavaCourse();
        user.studyGoCourse();
    }*/

    /*v2
    public static void main(String[] args) {
        User user = new User();
        user.study(new JavaStudy());
        user.study(new GoStudy());
        user.study(new PythonStudy());
    }*/

    //v3
    /*public static void main(String[] args) {
        User user = new User(new JavaStudy());
        user.study();
    }*/

    //v4
    public static void main(String[] args) {
        User user = new User();
        user.setIStudy(new PythonStudy());
        user.study();

        user.setIStudy(new GoStudy());
        user.study();
    }

    /*

     */
}
