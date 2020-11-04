package org.fufeng.project.problems;

import java.lang.reflect.Field;

/**
 * IntegerCase
 *  两个Integer数字，通过swap方法互换，引用的值是否会发生变化
 *  Integer 存在缓存(-128 - 127)
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/7 18:02
 */
public class IntegerCase {

	public static void main(String[] args) throws Exception {
		Integer i1=1,i2=2;
		System.out.printf("i1=[%d],i2=[%d]\n",i1,i2);
		//swap(i1,i2);
		swapEffective(i1,i2);
		System.out.printf("i1=[%d],i2=[%d]\n",i1,i2);

		System.out.println("main Integer.valueOf(1):"+Integer.valueOf(1));

		Integer i3 = new Integer(1);
		Integer i4 = new Integer(1);
		System.out.println(i3.equals(i4));
		System.out.println(i3==i4);

		Integer i5=2,i6=2;
		System.out.println(i5.equals(i6));
		System.out.println(i5==i6);
	}

	/**
	 *  java中的变量分为两种，一种是引用，一个是基本类型
	 *   这里在方法中传递过来的是引用对象的一个引用地址的副本，所以这里不会影响原方法的处理
	 * @param i1 value
	 * @param i2 value
	 */
	static void swap(Integer i1,Integer i2){
		Integer tmp = i1;
		i1 = i2;
		i2 = tmp;
	}

	/**
	 *  这里使用反射去设置Integer的value成员变量
	 * @param i1 value
	 * @param i2 value
	 */
	static void swapEffective(Integer i1,Integer i2) throws Exception {
		Field value = Integer.class.getDeclaredField("value");
		value.setAccessible(true);
		//int tmp = i1.intValue(); //这里不起作用
		Integer tmp = new Integer(i1.intValue());
		value.set(i1,i2.intValue());
		System.out.println("Integer.valueOf(1):"+Integer.valueOf(1));
		System.out.println("tmp:"+tmp);
		int val = 1;
		System.out.println("val:"+val);
		value.set(i2,tmp);
		System.out.println("Integer.valueOf(2):"+Integer.valueOf(2));

		Integer valTwo = 2;
		System.out.println(1+valTwo);
	}
}
