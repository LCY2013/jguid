/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @date : 2020-12-09
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.project.base.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description Lock BenchMark 测试
 * @create 2020-12-09
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
public class JMHLockTest {

    private static int count;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHLockTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(options).run();
    }

    @Benchmark
    @Threads(50)
    public void add() {
        for (int i = 0; i < 50000; i++) {
            lock.lock();
            try {
                count++;
            }finally {
                lock.unlock();
            }
        }
    }

}

/*
round 2

# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 50 threads, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: org.fufeng.project.base.jmh.JMHLockTest.add

# Run progress: 0.00% complete, ETA 00:01:40
# Fork: 1 of 1
# Warmup Iteration   1: 800.581 ops/s
# Warmup Iteration   2: 754.941 ops/s
# Warmup Iteration   3: 737.206 ops/s
# Warmup Iteration   4: 735.176 ops/s
# Warmup Iteration   5: 753.259 ops/s
Iteration   1: 708.007 ops/s
Iteration   2: 735.938 ops/s
Iteration   3: 740.385 ops/s
Iteration   4: 756.754 ops/s
Iteration   5: 619.076 ops/s


Result "org.fufeng.project.base.jmh.JMHLockTest.add":
  712.032 ±(99.9%) 211.193 ops/s [Average]
  (min, avg, max) = (619.076, 712.032, 756.754), stdev = 54.846
  CI (99.9%): [500.839, 923.225] (assumes normal distribution)


# Run complete. Total time: 00:01:46

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark         Mode  Cnt    Score     Error  Units
JMHLockTest.add  thrpt    5  712.032 ± 211.193  ops/s

Process finished with exit code 0
 */

/*
round 1

# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: org.fufeng.project.base.jmh.JMHLockTest.add

# Run progress: 0.00% complete, ETA 00:01:40
# Fork: 1 of 1
# Warmup Iteration   1: 1007.445 ops/s
# Warmup Iteration   2: 1155.483 ops/s
# Warmup Iteration   3: 1137.685 ops/s
# Warmup Iteration   4: 1095.688 ops/s
# Warmup Iteration   5: 1147.837 ops/s
Iteration   1: 1102.870 ops/s
Iteration   2: 1143.124 ops/s
Iteration   3: 1030.526 ops/s
Iteration   4: 1136.532 ops/s
Iteration   5: 1135.536 ops/s


Result "org.fufeng.project.base.jmh.JMHLockTest.add":
  1109.718 ±(99.9%) 180.814 ops/s [Average]
  (min, avg, max) = (1030.526, 1109.718, 1143.124), stdev = 46.957
  CI (99.9%): [928.904, 1290.531] (assumes normal distribution)


# Run complete. Total time: 00:01:45

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark         Mode  Cnt     Score     Error  Units
JMHLockTest.add  thrpt    5  1109.718 ± 180.814  ops/s

Process finished with exit code 0
 */
