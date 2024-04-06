package org.fufeng.design.pattern.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 15:32
 */
public class Teacher implements Observer {
    private String teacherName;

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public void update(Observable o, Object arg) {
        Course course = (Course) o;
        Question question = (Question) arg;
        System.out.println("===============================");
        System.out.println(teacherName + "老师，你好！\n" +
                "在" + course.getCourseName() + "课程中，" +
                "您收到了一个来自“" + question.getUserName() + "”的提问，希望您解答，问题内容如下：\n" +
                question.getQuestionContent());
    }
}
