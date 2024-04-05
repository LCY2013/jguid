package org.fufeng.design.pattern.structural.decorator.v2;

/**
 * 装饰器模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:24
 */
public abstract class AbstractDecorator extends ABattercake {

    private ABattercake aBattercake;

    public AbstractDecorator(ABattercake aBattercake) {
        this.aBattercake = aBattercake;
    }

    protected abstract void dosomething();

    @Override
    protected String getDesc() {
        return this.aBattercake.getDesc();
    }

    @Override
    protected int cost() {
        return this.aBattercake.cost();
    }

}
