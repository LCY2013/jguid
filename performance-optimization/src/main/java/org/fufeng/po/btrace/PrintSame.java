package org.fufeng.po.btrace;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.BTrace;
import org.openjdk.btrace.core.annotations.OnMethod;
import org.openjdk.btrace.core.annotations.ProbeClassName;
import org.openjdk.btrace.core.annotations.ProbeMethodName;

/**
 * btrace PID PrintSame.java
 */
@BTrace
public class PrintSame {
	
	@OnMethod(
	        clazz="org.fufeng.po.btrace.BtraceController",
	        method="same"
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, String name) {
		BTraceUtils.println(pcn+","+pmn + "," + name);
		BTraceUtils.println();
    }

	@OnMethod(
			clazz="org.fufeng.po.btrace.BtraceController",
			method="same"
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, String name, int id) {
		BTraceUtils.println(pcn+","+pmn + "," + name + "," +id);
		BTraceUtils.println();
	}
}
