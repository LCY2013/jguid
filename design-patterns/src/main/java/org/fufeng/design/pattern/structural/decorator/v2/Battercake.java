package org.fufeng.design.pattern.structural.decorator.v2;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:16
 */
public class Battercake extends ABattercake {

    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
