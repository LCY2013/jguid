/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-11-15
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.loom.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Custom {@link ThreadFactory} that is used from all the places that create threads.
 * Setting the {@code -DvirtualThreads=true} turns each thread into a virtual one.
 *
 * @author Mark Paluch
 */
public class GlobalThreadFactory implements ThreadFactory {

    // We want to limit the number of kernel threads that host our VirtualThreads.
    // If we don't set the executor, a default ForkJoin pool gets spun up that seems to be subject to unbounded growth.
    public static Executor host = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static boolean useVirtualThreads = Boolean
            .parseBoolean(System.getProperty("virtualThreads", "false"));

    // Just to record some stats.
    public static AtomicLong threadsCreated = new AtomicLong();

    public final AtomicLong threadCounter = new AtomicLong();
    private final String threadName;
    private final boolean daemon;

    public GlobalThreadFactory(String threadName, boolean daemon) {
        this.threadName = threadName;
        this.daemon = daemon;
    }

    static void customize(Thread.Builder builder) {

        // Note that VirtualThreads can't have a ThreadGroup
        if (useVirtualThreads) {
            builder.virtual(host);
        }
    }

    public static Thread create(Runnable runnable, String name) {

        Thread.Builder builder = Thread.builder().name(name).task(runnable);
        customize(builder);
        return doBuild(builder);
    }

    public static Thread create(Runnable runnable, String name, int threadPriority, boolean daemon) {

        Thread.Builder builder = Thread.builder().name(name).task(runnable)
                .priority(threadPriority).daemon(daemon);
        customize(builder);
        return doBuild(builder);
    }

    @Override
    public Thread newThread(Runnable runnable) {

        Thread.Builder builder = Thread.builder()
                .name(threadName + "-" + threadCounter.incrementAndGet())
                .task(runnable)
                .daemon(daemon);

        customize(builder);

        return doBuild(builder);
    }

    private static Thread doBuild(Thread.Builder builder) {

        Thread thread = builder.build();

        if (useVirtualThreads) {
            System.out.println("VirtualThread: " + threadsCreated
                    .incrementAndGet() + " -> " + thread.getName());
        }
        else {
            System.out.println("Thread: " + threadsCreated
                    .incrementAndGet() + " -> " + thread.getName());
        }
        return thread;
    }

}