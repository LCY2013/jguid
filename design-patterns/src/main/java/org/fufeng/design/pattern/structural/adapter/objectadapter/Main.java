package org.fufeng.design.pattern.structural.adapter.objectadapter;

/**
 * 对象适配器模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 19:46
 */
public class Main {

    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();

        Target adapterTarget = new Adapter();
        adapterTarget.request();
    }

}
