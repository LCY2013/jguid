package org.fufeng.project.unsafe;

import org.fufeng.project.lamda.MagicPrint;
import org.fufeng.project.lamda.MagicPrintStream;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.function.Consumer;

import static java.lang.System.out;

/**
 * Demo
 *
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/26 11:30
 */
public class Demo {

	public static void main(String[] args) {
		//test05();
		test();
	}

	static void test05(){
		MagicPrint<String,Integer> magicPrint = MagicPrintStream::println;
		magicPrint.andThen(magicPrint).print("magic",1314);
	}

	static void test04(){
		//Consumer<String> consumer = s -> System.out.println(s);
		Consumer<String> consumer = out::println;
		consumer.accept("hello magic");
		Consumer<String> consumer1 = out::println;
		//consumer1.accept("hello yyr");
		consumer.andThen(consumer1);
	}

	static void test01(){
		String s1 = "1";
		String s2 = "2";
		String s3 = s1+s2;
		String s4 = s3 + "3";
		out.println(s4);
	}

	static void test02(){
		ByteBuffer buffer = ByteBuffer.allocateDirect(10);
		buffer.put(new byte[]{1,2,3,4});
		out.println("刚写完数据 " +buffer);
		buffer.flip();
		out.println("flip之后 " +buffer);
		buffer.put(new byte[]{5,6,7,8});
		out.println("flip之后,继续put " +buffer);
		buffer.flip();
		out.println("flip之后,继续flip " +buffer);
		byte[] target = new byte[buffer.limit()];
		//自动读取target.length个数据
		buffer.get(target);
		for(byte b : target){
			out.println(b);
		}
		out.println("读取完数组 " +buffer);
	}

	static void test(){
		Unsafe unsafe = null;
		try {
			final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
				public Unsafe run() throws Exception {
					Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
					theUnsafe.setAccessible(true);
					return (Unsafe) theUnsafe.get(null);
				}
			};
			unsafe = AccessController.doPrivileged(action);
		}
		catch (Exception e){
			throw new RuntimeException("Unable to load unsafe", e);
		}
		unsafe.allocateMemory(1L);
	}
}
