package org.fufeng.design.pattern.creational.singleton;

import java.io.*;

/**
 * 序列化反序列化测试破坏单例模式
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 10:59
 */
public class SerializableObjectMain {

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        try {
            // 序列化
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.obj"));
            oos.writeObject(instance);
            oos.close();

            // 反序列化
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.obj"));
            HungrySingleton newInstance = (HungrySingleton) ois.readObject();
            ois.close();

            System.out.println(instance);
            System.out.println(newInstance);
            System.out.println(instance == newInstance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //删除文件
            new File("singleton.obj").delete();
        }
    }

}
