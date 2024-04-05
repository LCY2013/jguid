package org.fufeng.design.pattern.creational.prototype.clone;

import java.util.Date;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:23
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Date birthday = new Date(0L);
        Pig pig = new Pig("佩奇", birthday);
        Pig clonePig = (Pig) pig.clone();
        System.out.println(pig);
        System.out.println(clonePig);
        System.out.println(clonePig == pig);

        pig.getBirthday().setTime(birthday.getTime()+67890L);
        System.out.println(pig);
        System.out.println(clonePig);
        System.out.println(clonePig == pig);
    }
}
