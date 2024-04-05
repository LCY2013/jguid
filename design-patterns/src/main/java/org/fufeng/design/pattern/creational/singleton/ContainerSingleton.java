package org.fufeng.design.pattern.creational.singleton;


import java.util.HashMap;
import java.util.Map;

/**
 * 容器单例
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 16:26
 */
public class ContainerSingleton {

    private ContainerSingleton() {
    }

    private static Map<String, Object> singletonMap = new HashMap<>();

    public static void putInstance(String key, Object instance) {
        if (!key.trim().isEmpty() && instance != null && !singletonMap.containsKey(key)) {
            singletonMap.put(key, instance);
        }
    }

    public static Object getInstance(String key) {
        return singletonMap.get(key);
    }
}
