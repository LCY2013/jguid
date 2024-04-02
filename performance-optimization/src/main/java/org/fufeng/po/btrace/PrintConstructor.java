package org.fufeng.po.btrace;


import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;
import org.openjdk.btrace.core.types.AnyType;

/**
 * btrace PID PrintConstructor.java
 */
@BTrace
public class PrintConstructor {
	
	@OnMethod(
	        clazz="org.fufeng.po.domain.User",
	        method="<init>"/*,
			location=@Location(
			value = Kind.CALL,
			clazz = "org.fufeng.po.domain.User",
			method = "<init>",
			where = Where.AFTER)*/
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
		BTraceUtils.println(pcn+","+pmn);
		BTraceUtils.printArray(args);
		BTraceUtils.println();
    }
}
