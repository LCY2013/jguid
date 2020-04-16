package com.lcydream.project.jvm;

/**
 * @program: jguid
 * @description: class 字节码
 *  <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5"><a/>
 *  javap -verbose
 *
 *  iconst_<i>
 * Operation
 * Push int constant
 *
 * Format
 *
 * iconst_<i>
 * Forms
 * iconst_m1 = 2 (0x2)
 *
 * iconst_0 = 3 (0x3)
 *
 * iconst_1 = 4 (0x4)
 *
 * iconst_2 = 5 (0x5)
 *
 * iconst_3 = 6 (0x6)
 *
 * iconst_4 = 7 (0x7)
 *
 * iconst_5 = 8 (0x8)
 *
 * Operand Stack
 * ... →
 *
 * ..., <i>
 *
 * Description
 * Push the int constant <i> (-1, 0, 1, 2, 3, 4 or 5) onto the operand stack.
 *
 * Notes
 * Each of this family of instructions is equivalent to bipush <i> for the respective value of <i>, except that the operand <i> is implicit.
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-04-15 14:41
 */
public class ClassStructTest {

    public static void main(String[] args) {
        int a = 1;
        int b = 3;
        int c = a + b;
        int d = 127;
        int e = 128;
        int f = 129;
        long g = f;
        int h = 6;
        ClassStructTest classStructTest = new ClassStructTest();
        System.out.println(c);
    }

}
