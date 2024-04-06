package org.fufeng.design.pattern.behavioral.observer;

import java.util.Observable;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 12:23
 */
public class Course extends Observable {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void produceQuestion(Course course, Question question) {
        System.out.println(question.getUserName() + "在" + this.courseName + "课程上提交了一个问题");
        setChanged();
        notifyObservers(question);
    }
}
