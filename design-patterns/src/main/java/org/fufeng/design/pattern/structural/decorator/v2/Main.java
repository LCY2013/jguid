package org.fufeng.design.pattern.structural.decorator.v2;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * {@link Reader}
 * {@link BufferedReader}
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 18:28
 */
public class Main {

    public static void main(String[] args) {
        ABattercake aBattercake = new Battercake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);
        System.out.println(aBattercake.getDesc()+ " 销售价格："+aBattercake.cost());
    }

}
