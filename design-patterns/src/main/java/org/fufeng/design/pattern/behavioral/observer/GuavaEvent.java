package org.fufeng.design.pattern.behavioral.observer;

import com.google.common.eventbus.Subscribe;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 15:53
 */
public class GuavaEvent {

    @Subscribe
    public void subscribe(String event) {
        System.out.println("guava event subscribe: " + event);
    }
}
