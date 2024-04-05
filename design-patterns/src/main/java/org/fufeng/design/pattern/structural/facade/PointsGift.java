package org.fufeng.design.pattern.structural.facade;

/**
 *  外观模式(门面模式)
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:53
 */
public class PointsGift {

    private String name;

    public PointsGift(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
