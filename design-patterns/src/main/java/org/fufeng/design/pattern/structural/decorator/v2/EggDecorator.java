package org.fufeng.design.pattern.structural.decorator.v2;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:25
 */
public class EggDecorator extends AbstractDecorator {
    public EggDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected void dosomething() {
        // do something
    }


    @Override
    protected String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    protected int cost() {
        return super.cost() + 1;
    }
}
