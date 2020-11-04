package org.fufeng.project.jvm.gc;

/**
 * MethodAreaGC
 * 方法区的一些废弃常量，不用类等信息可以被回收，
 * HotSpot虚拟机提供-Xnoclassgc进行控制，还可以使用-verbose:class
 * 以及-XX:+TraceClassLoading、-XX:+TraceClassUnLoading查看类的加载和卸载信息
 *  VM: -Xnoclassgc -verbose:class -XX:+TraceClassLoading product类型的虚拟机支持
 *  -XX:+TraceClassUnLoading 这个参数需要fastdebug类型的虚拟机支持
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/2/27 15:28
 */
public class MethodAreaGC {

	public static void main(String[] args) {

	}
}
