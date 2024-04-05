package org.fufeng.design.pattern.creational.singleton;

import java.io.Serial;
import java.io.Serializable;

/**
 * 单例模式-饿汉式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:54
 */
public class HungrySingleton implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //v1
    //private static final HungrySingleton instance = new HungrySingleton();

    //v2
    private static final HungrySingleton instance;

    static {
        instance = new HungrySingleton();
    }

    private HungrySingleton() {
        // 防止反射调用
        if (instance != null) {
            throw new RuntimeException("单例构造器禁止反射调用");
        }
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    private Object readResolve() {
        return instance;
    }
}
