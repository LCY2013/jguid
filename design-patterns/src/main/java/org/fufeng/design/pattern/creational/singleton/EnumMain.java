package org.fufeng.design.pattern.creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * 枚举实现单例模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 16:08
 */
public class EnumMain {

    public static void main(String[] args) {
        // 1、枚举单例模式序列化测试
        /*try {
            EnumInstance instance = EnumInstance.getInstance();
            instance.setValue(new Object());
            // 序列化
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.obj"));
            oos.writeObject(instance);
            oos.close();

            // 反序列化
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.obj"));
            EnumInstance newInstance = (EnumInstance) ois.readObject();
            ois.close();

            System.out.println(instance);
            System.out.println(newInstance);
            System.out.println(instance == newInstance);
            System.out.println("==============");
            System.out.println(instance.getValue());
            System.out.println(newInstance.getValue());
            System.out.println(instance.getValue() == newInstance.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //删除文件
            new File("singleton.obj").delete();
        }*/

        // 2、枚举单例模式反射测试
        /*try {
            EnumInstance instance = EnumInstance.getInstance();
            instance.setValue(new Object());
            // 反射
            Class<?> clazz = EnumInstance.class;
            // 报错
            // 原因：protected Enum(String name, int ordinal) {
            //        this.name = name;
            //        this.ordinal = ordinal;
            //    }
            //EnumInstance newInstance = (EnumInstance) clazz.newInstance();
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class);
            declaredConstructor.setAccessible(true);
            //Cannot reflectively create enum objects
            EnumInstance newInstance = (EnumInstance) declaredConstructor.newInstance("fufeng", 666);

            System.out.println(instance);
            System.out.println(newInstance);
            System.out.println(instance == newInstance);
            System.out.println("==============");
            System.out.println(instance.getValue());
            System.out.println(newInstance.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        EnumInstance instance = EnumInstance.getInstance();
        instance.printTest();
    }

}
