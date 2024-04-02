package org.fufeng.po.btrace;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;
import org.openjdk.btrace.core.types.AnyType;

/**
 * btrace PID PrintReturn.java
 */
@BTrace
public class PrintReturn {
	
	@OnMethod(
	        clazz="org.fufeng.po.btrace.BtraceController",
	        method="arg1",
	        location=@Location(Kind.RETURN)
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, @Return AnyType result) {
		BTraceUtils.println(pcn+","+pmn + "," + result);
		BTraceUtils.println();
    }
}
