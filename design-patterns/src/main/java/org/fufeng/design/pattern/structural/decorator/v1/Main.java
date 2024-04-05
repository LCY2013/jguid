package org.fufeng.design.pattern.structural.decorator.v1;

/**
 * 非装饰器模式版本
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:19
 */
public class Main {

    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getDesc()+ " 销售价格："+battercake.cost());

        BattercakeWithEgg battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getDesc()+ " 销售价格："+battercakeWithEgg.cost());

        BattercakeWithEggSausage battercakeWithEggSausage = new BattercakeWithEggSausage();
        System.out.println(battercakeWithEggSausage.getDesc()+ " 销售价格："+battercakeWithEggSausage.cost());
    }

}
