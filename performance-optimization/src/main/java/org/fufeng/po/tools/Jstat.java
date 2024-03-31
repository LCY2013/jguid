package org.fufeng.po.tools;

/**
 * jstat 查看jvm统计信息
 * <a href="https://docs.oracle.com/en/java/javase/21/docs/specs/man/jstat.html">jstat</a>
 * 类装载
 * 垃圾收集
 * JIT编译
 * <p>
 * jstat -help
 * jstat -help|-options
 * options:
 * -class：显示有关类加载器行为的统计信息。
 * -compiler：显示有关 Java HotSpot VM 即时编译器行为的统计信息。
 * -gc：显示有关垃圾收集堆行为的统计信息。
 * -gccapacity：显示各代容量及其对应空间的统计信息。
 * -gccause：显示有关垃圾收集统计信息的摘要（与 相同-gcutil），以及上次和当前（如果适用）垃圾收集事件的原因。
 * -gcnew：显示新生代行为的统计信息。
 * -gcnewcapacity：显示新生代大小及对应空间的统计信息。
 * -gcold：显示有关老年代行为的统计信息和元空间统计信息。
 * -gcoldcapacity：显示老年代大小的统计信息。
 * -gcmetacapacity：显示有关元空间大小的统计信息。
 * -gcutil：显示有关垃圾收集统计信息的摘要。
 * -printcompilation：显示Java HotSpot VM编译方法统计信息
 *
 * @author fufeng
 * @Date 2024-03-31 16:14
 */
public class Jstat {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

}
