package org.fufeng.po.btrace;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.BTrace;
import org.openjdk.btrace.core.annotations.OnMethod;
import org.openjdk.btrace.core.annotations.ProbeClassName;
import org.openjdk.btrace.core.annotations.ProbeMethodName;

@BTrace
public class PrintRegex {
	
	@OnMethod(
	        clazz="org.fufeng.po.btrace.BtraceController",
	        method="/.*/"
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn) {
		BTraceUtils.println(pcn+","+pmn);
		BTraceUtils.println();
    }
}
