package org.fufeng.po.btrace;

import org.fufeng.po.domain.User;
import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;

import java.lang.reflect.Field;

/**
 * btrace -cp "/Users/chunyunluo/developer/java/projects/jguid/performance-optimization/target/classes" 24483 PrintArgComplex.java
 */
@BTrace
public class PrintArgComplex {
	
	
	@OnMethod(
	        clazz="org.fufeng.po.btrace.BtraceController",
	        method="arg2",
	        location=@Location(Kind.ENTRY)
	)
	public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, User user) {
		//print all fields
		BTraceUtils.printFields(user);
		//print one field
		Field filed2 = BTraceUtils.field("org.fufeng.po.domain.User", "name");
		BTraceUtils.println(BTraceUtils.get(filed2, user));
		BTraceUtils.println(pcn+","+pmn);
		BTraceUtils.println();
    }
}
