package org.fufeng.po.btrace;


import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;
import org.openjdk.btrace.core.types.AnyType;

/**
 * btrace PID PrintArgSimple.java
 */
@BTrace
public class PrintArgSimple {
	
	@OnMethod(
	        clazz="org.fufeng.po.btrace.BtraceController",
	        method="arg1",
	        location=@Location(Kind.ENTRY)
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
		BTraceUtils.printArray(args);
		BTraceUtils.println(pcn+","+pmn);
		BTraceUtils.println();
    }
}
