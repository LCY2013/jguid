package org.fufeng.design.pattern.creational.factorymethod;

/**
 *
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 19:33
 */
public class GoVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

}
