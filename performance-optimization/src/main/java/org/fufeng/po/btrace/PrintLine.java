package org.fufeng.po.btrace;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;

/**
 * btrace PID PrintLine.java
 */
@BTrace
public class PrintLine {
	
	@OnMethod(
	        clazz="org.fufeng.po.btrace.BtraceController",
	        method="exception",
	        location=@Location(value= Kind.LINE, line=40)
//	        location=@Location(value= Kind.LINE, line=-1)
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
		BTraceUtils.println(pcn+","+pmn + "," +line);
		BTraceUtils.println();
    }
}
