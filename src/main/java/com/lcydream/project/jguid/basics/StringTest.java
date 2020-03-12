package com.lcydream.project.jguid.basics;

/**
 * 1.==和equals的区别==
 * 对于基本数据类型来说，是用于比较“值”是否相等的；而对于引用类型来说，是用于比较引用地址是否相同的。
 * 查看源码我们可以知道 Object 中也有 equals()  方法，源码{@link Object#equals(Object)}
 * 可以看出，Object 中的 equals() 方法其实就是 ==，而 String 重写了 equals()
 * 方法把它修改成比较两个字符串的值是否相等，源码{@link String#equals(Object)}
 *
 * 2. final 修饰的好处
 *  那这样设计有什么好处呢？Java语言之父JamesGosling的回答是，他会更倾向于使用final，
 *  因为它能够缓存结果，如果是可变类的话，则有可能需要重新拷贝出来一个新值进行传参，这样在性能上就会有一定的损失。
 *  James Gosling 还说迫使 String 类设计成不可变的另一个原因是安全，当你在调用其他方法时，
 *  比如调用一些系统级操作指令之前，可能会有一系列校验，如果是可变类的话，可能在你校验过后，
 *  它的内部的值又被改变了，这样有可能会引起严重的系统崩溃问题，这是迫使 String 类设计成不可变类的一个重要原因。
 *  总结来说，使用 final 修饰的第一个好处是安全；第二个好处是高效，以 JVM 中的字符串常量池来举例，
 *  如下两个变量：
 *      String s1 = "java";
 *      String s2 = "java";
 *   只有字符串是不可变时，我们才能实现字符串常量池，字符串常量池可以为我们缓存字符串，提高程序的运行效率
 *   试想一下如果 String 是可变的，那当 s1 的值修改之后，s2 的值也跟着改变了，
 *   这样就和我们预期的结果不相符了，因此也就没有办法实现字符串常量池的功能了。
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "java";
        String s2 = "java";
        String s3 = new String("java");
        String s4 = String.valueOf("java");
        String s5 = "ja" + "va";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s1 == s3.intern());
        System.out.println(s1 == s5);
    }
}
