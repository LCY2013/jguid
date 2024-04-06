package org.fufeng.design.pattern.behavioral.observer;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 15:31
 */
public class Question {

    private String userName;
    private String questionContent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
