package com.lcydream.project.jguid.dynamic.proxy.jdk;

import com.lcydream.project.jguid.dynamic.proxy.jdk.aop.Aop;
import com.lcydream.project.jguid.dynamic.proxy.jdk.aop.LoggerAop;
import com.lcydream.project.jguid.dynamic.proxy.jdk.aop.TimeAop;
import com.lcydream.project.jguid.dynamic.proxy.jdk.business.LoggerFmt;
import com.lcydream.project.jguid.dynamic.proxy.jdk.business.LoggerInterface;
import com.lcydream.project.jguid.dynamic.proxy.jdk.business.LoggerPrint;
import com.lcydream.project.jguid.dynamic.proxy.jdk.proxy.LoggerInvokeHandler;
import com.lcydream.project.jguid.dynamic.proxy.jdk.proxy.LoggerProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JDkProxyTest
 * @Description TODO
 * @Author fufeng1226
 * @Date 2019/12/3 4:22 下午
 **/
public class JDkProxyTest {

    public static void main(String[] args) {

        final LoggerInterface loggerInterface =
                LoggerProxy.getLoggerInterface(new LoggerInvokeHandler(new LoggerFmt()));

        System.out.println(loggerInterface.fmt(new Object()).getClass());

        List<Aop> aops = new ArrayList<>();
        aops.add(new LoggerAop());
        aops.add(new TimeAop());
        final LoggerInterface loggerInterfac =
                LoggerProxy.getLoggerInterface(new LoggerInvokeHandler(new LoggerPrint(),aops));
        System.out.println(loggerInterfac.fmt(loggerInterfac));

    }
}
