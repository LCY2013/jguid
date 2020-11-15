/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tomcat.util.threads;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Mark Paluch
 */
public class VirtualTomcatExecutor implements TomcatExecutor {

    private final ExecutorService delegate;

    public VirtualTomcatExecutor(ExecutorService delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getPoolSize() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaximumPoolSize() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getSubmittedCount() {
        return 0;
    }

    @Override
    public void stopCurrentThreadIfNeeded() {

    }

    @Override
    public boolean currentThreadShouldBeStopped() {
        return false;
    }

    @Override
    public long getKeepAliveTime(TimeUnit unit) {
        return unit.convert(60, TimeUnit.SECONDS);
    }

    @Override
    public void shutdown() {
        delegate.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return delegate.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return delegate.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return delegate.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return delegate.awaitTermination(timeout, unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return delegate.submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return delegate.submit(task, result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return delegate.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return delegate.invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, boolean cancelOnException) throws InterruptedException {
        return delegate.invokeAll(tasks, cancelOnException);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return delegate.invokeAll(tasks, timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return delegate.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return delegate.invokeAny(tasks, timeout, unit);
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public ExecutorService withDeadline(Instant deadline) {
        return delegate.withDeadline(deadline);
    }

    @Override
    public <T> CompletableFuture<T> submitTask(Callable<T> task) {
        return delegate.submitTask(task);
    }

    @Override
    public <T> List<CompletableFuture<T>> submitTasks(Collection<? extends Callable<T>> tasks) {
        return delegate.submitTasks(tasks);
    }

    @Override
    public void execute(Runnable command) {
        delegate.execute(command);
    }
}