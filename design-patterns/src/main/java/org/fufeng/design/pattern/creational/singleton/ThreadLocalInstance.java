package org.fufeng.design.pattern.creational.singleton;

/**
 * threadlocal 单例
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 16:41
 */
public class ThreadLocalInstance {

    private static final ThreadLocal<ThreadLocalInstance> threadLocal = ThreadLocal.withInitial(ThreadLocalInstance::new);

    private ThreadLocalInstance() {
    }

    public static ThreadLocalInstance getInstance() {
        return threadLocal.get();
    }

}
