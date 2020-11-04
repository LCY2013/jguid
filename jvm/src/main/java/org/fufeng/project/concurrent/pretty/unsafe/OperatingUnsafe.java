package org.fufeng.project.concurrent.pretty.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName: UnsafeOpration
 * @author: LuoChunYun
 * @Date: 2019/5/19 17:12
 * @Description: TODO
 */
public class OperatingUnsafe {

    /**
     * 设置一个变量
     */
    private volatile long state = 0;

    /**
     * 记录变量state在类OperatingUnsafe中的偏移量
     */
    static final long stateOffset;

    /**
     * 获取Unsafe实例
     */
    static Unsafe unsafe ; //= Unsafe.getUnsafe(); jdk不允许直接使用unsafe对象

    static {
        //获取state变量在类OperatingUnsafe中的偏移量
        try {

            //使用反射获取unsafe的成员变量theUnsafe
            final Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");

            //设置为可访问的
            theUnsafeField.setAccessible(true);

            //获取该变量的值
            unsafe = (Unsafe)theUnsafeField.get(null);

            //获取state成员变量在OperatingUnsafe类中的偏移量
            stateOffset = unsafe.objectFieldOffset(OperatingUnsafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        //创建实例，并且设置state的值2
        OperatingUnsafe operatingUnsafe = new OperatingUnsafe();

        final boolean sucess = unsafe.compareAndSwapInt(
                operatingUnsafe, stateOffset, 0, 2);
        System.out.println(sucess + ":" + operatingUnsafe.state);
    }
}
