package org.fufeng.design.pattern.behavioral.mediator;

/**
 * {@link java.util.Timer}
 *
 * @author fufeng
 * {@code @Date} 2024-04-06 17:38
 */
public class Main {

    public static void main(String[] args) {
        User user = new User("fufeng");
        User tom = new User("tom");
        user.sendMessage("hello");
        tom.sendMessage("hello");
    }

}
