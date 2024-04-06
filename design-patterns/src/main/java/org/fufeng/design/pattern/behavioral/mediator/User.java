package org.fufeng.design.pattern.behavioral.mediator;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 17:38
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        StudyGroup.showMessage(this, message);
    }
}
