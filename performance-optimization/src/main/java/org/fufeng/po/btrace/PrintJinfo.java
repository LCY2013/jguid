package org.fufeng.po.btrace;


import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.BTrace;

/**
 * btrace PID PrintJinfo.java
 */
@BTrace
public class PrintJinfo {
    static {
    	BTraceUtils.println("System Properties:");
    	BTraceUtils.printProperties();
    	BTraceUtils.println("VM Flags:");
    	BTraceUtils.printVmArguments();
    	BTraceUtils.println("OS Enviroment:");
    	BTraceUtils.printEnv();
    	BTraceUtils.exit(0);
    }
}