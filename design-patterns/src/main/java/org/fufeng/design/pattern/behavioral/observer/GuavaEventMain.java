package org.fufeng.design.pattern.behavioral.observer;

import com.google.common.eventbus.EventBus;

/**
 * @author fufeng
 * {@code @Date} 2024-04-06 15:54
 */
public class GuavaEventMain {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("guava event bus");
    }

}
