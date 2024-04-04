package org.fufeng.design.pattern.creational.factorymethod;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 19:34
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

}
