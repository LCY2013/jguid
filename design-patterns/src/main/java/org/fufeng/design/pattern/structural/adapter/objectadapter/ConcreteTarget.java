package org.fufeng.design.pattern.structural.adapter.objectadapter;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 19:42
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("ConcreteTarget request");
    }

}
