package org.fufeng.design.pattern.behavioral.observer;


import com.google.common.eventbus.Subscribe;

/**
 * 监视器模式
 * {@link java.awt.Event}
 * {@link org.springframework.web.context.request.RequestContextListener}
 * {@link Subscribe}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 15:40
 */
public class Main {

    public static void main(String[] args) {
        Course course = new Course("java");
        Teacher teacher = new Teacher("fufeng");
        Teacher magic = new Teacher("magic");

        course.addObserver(teacher);
        course.addObserver(magic);

        //业务逻辑代码
        Question question = new Question();
        question.setUserName("小明");
        question.setQuestionContent("java如何学习");

        course.produceQuestion(course, question);
    }
}
