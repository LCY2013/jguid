package org.fufeng.project.jvm.outofmemory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * RuntimeConstantPoolOOM
 *  VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *  通过设置永久代的大小，间接设置方法区的大小,但是8以后没有永久代,所以以本地内存为主
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 10:37
 */
public class JavaMethodAreaOOM {

	static class OOMObject{}

	public static void main(String[] args) {

		while (true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj,args);
				}
			});
			enhancer.create();
		}

	}
}
