package org.fufeng.po.vmargs;

/**
 * 命令行参数
 * 1、标准参数
 * 2、X参数
 *  非标准参数
 * <p>
 * 编译模式：
 * java -Xint -version: 解释执行
 * java -Xcomp -version: 编译执行
 * java -Xmixed -version: 混合执行
 * 3、XX参数
 * Boolean 类型
 *   -XX:[+-]<name> 禁用启用name
 *   -XX:+UseConcMarkSweepGC
 *   -XX:+UseParNewGC
 *   -XX:+UseParallelGC
 *   -XX:+UseSerialGC
 *   -XX:+UseG1GC
 * 非Boolean类型
 *   -XX:<name>=<value>表示name属性的值是value
 *   -XX:MaxGCPauseMillis=500
 *   -XX:GCTimeRatio=19
 * -Xms -Xmx
 * -Xms 等价于 -XX:InitialHeapSize=Xms
 * -Xmx 等价于 -XX:MaxHeapSize=Xmx
 * <p>
 * jinfo -flag <name> PID
 *  查看某个进程最大堆内存：jinfo -flag MaxHeapSize PID
 *  -XX:MaxHeapSize=2147483648
 *
 * <p>
 * 查看JVM运行时参数
 * -XX:+PrintCommandLineFlags 查看命令行参数
 * -XX:+PrintFlagsFinal 查看最终参数
 * -XX:+PrintFlagsInitial 查看初始化参数
 * -XX:+UnlockExperimentalVMOptions 解锁实验性功能
 * -XX:+UnlockDiagnosticVMOptions 解锁诊断功能
 * -XX:+PrintVMOptions 查看所有参数
 * java -XX:+PrintFlagsFinal --version
 * = 是默认值 := 覆盖默认值
 *
 * @author fufeng
 * @Date 2024-03-31 15:56
 */
public class Args {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

}
