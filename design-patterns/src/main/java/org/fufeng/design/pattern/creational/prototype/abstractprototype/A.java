package org.fufeng.design.pattern.creational.prototype.abstractprototype;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:20
 */
public abstract class A implements Cloneable {


    @Override
    public A clone() {
        try {
            return (A) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
