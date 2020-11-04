package org.fufeng.project.concurrent.fatory;

/**
 * @ClassName: CaptureUncaughtExceptionHandler
 * @author: LuoChunYun
 * @Date: 2019/5/18 17:18
 * @Description: TODO
 */
public class CaptureUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("CaptureUncaughtExceptionHandler:"+e);
    }
}
