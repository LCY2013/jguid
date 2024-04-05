package org.fufeng.design.pattern.structural.decorator.v2;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:25
 */
public class SausageDecorator extends AbstractDecorator {
    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected void dosomething() {
        //do something
    }

    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一根烤肠";
    }

    @Override
    protected int cost() {
        return super.cost() + 2;
    }
}
