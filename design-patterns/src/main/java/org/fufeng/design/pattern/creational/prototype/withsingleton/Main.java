package org.fufeng.design.pattern.creational.prototype.withsingleton;

import java.lang.reflect.Method;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 17:31
 */
public class Main {

    public static void main(String[] args) throws Exception {
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        Method clone = hungrySingleton.getClass().getDeclaredMethod("clone");
        clone.setAccessible(true);

        HungrySingleton hungrySingleton1 = (HungrySingleton)clone.invoke(hungrySingleton);
        System.out.println(hungrySingleton);
        System.out.println(hungrySingleton1);
    }

}
