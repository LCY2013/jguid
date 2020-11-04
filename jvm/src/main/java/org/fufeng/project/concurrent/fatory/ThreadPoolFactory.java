package org.fufeng.project.concurrent.fatory;

import java.util.UUID;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName: ThreadPoolFactory
 * @author: LuoChunYun
 * @Date: 2019/5/18 17:13
 * @Description: 定义线程池的创建方式
 */
public class ThreadPoolFactory implements ThreadFactory {

    private volatile int MAX_NUM=10;

    private volatile int CURRENT_NUM=0;

    private boolean CURRENT_NEW=false;

    private boolean CURRENT_DEAMON=false;

    public ThreadPoolFactory() {
    }

    public ThreadPoolFactory(boolean CURRENT_NEW) {
        this.CURRENT_NEW = CURRENT_NEW;
    }

    public ThreadPoolFactory(boolean CURRENT_NEW,boolean CURRENT_DEAMON) {
        this.CURRENT_NEW = CURRENT_NEW;
        this.CURRENT_DEAMON = CURRENT_DEAMON;
    }

    public ThreadPoolFactory(int MAX_NUM,int CURRENT_NUM) {
        this.MAX_NUM = MAX_NUM;
        this.CURRENT_NUM = CURRENT_NUM;
    }

    public ThreadPoolFactory(int MAX_NUM,int CURRENT_NUM,boolean CURRENT_NEW,boolean CURRENT_DEAMON) {
        this.MAX_NUM = MAX_NUM;
        this.CURRENT_NUM = CURRENT_NUM;
        this.CURRENT_NEW = CURRENT_NEW;
        this.CURRENT_DEAMON = CURRENT_DEAMON;
    }

    @Override
    public Thread newThread(Runnable r) {
        if(CURRENT_NUM < MAX_NUM) {
            Thread newThread = new Thread(r);
            newThread.setName("fufeng1226-pool-" + CURRENT_NUM++);
            newThread.setDaemon(CURRENT_DEAMON);
            newThread.setUncaughtExceptionHandler(new CaptureUncaughtExceptionHandler());
            return newThread;
        }else if(CURRENT_NEW){
            MAX_NUM *= 2;
            Thread newThread = new Thread(r);
            newThread.setDaemon(CURRENT_DEAMON);
            newThread.setName("fufeng1226-pool-" + CURRENT_NUM++);
            newThread.setUncaughtExceptionHandler(new CaptureUncaughtExceptionHandler());
            return newThread;
        }
        return null;
    }

}
