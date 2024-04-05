package org.fufeng.design.pattern.structural.decorator.v1;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:17
 */
public class BattercakeWithEgg extends Battercake {

    @Override
    public String getDesc() {
        return super.getDesc() + " 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }

}
