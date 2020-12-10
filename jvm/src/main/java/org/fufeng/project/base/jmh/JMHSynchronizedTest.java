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

/**
 * @author <a href="https://github.com/lcy2013">MagicLuo(扶风)</a>
 * @program jguid
 * @description Synchronized Benchmark 相关测试数据
 * @create 2020-12-09
 */
@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
public class JMHSynchronizedTest {

    private final static Object LOCK = new Object();

    private static int count;

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(JMHSynchronizedTest.class.getSimpleName())
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
            synchronized (LOCK) {
                count++;
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
# Benchmark: org.fufeng.project.base.jmh.JMHSynchronizedTest.add

# Run progress: 0.00% complete, ETA 00:01:40
# Fork: 1 of 1
# Warmup Iteration   1: 1183.253 ops/s
# Warmup Iteration   2: 1419.584 ops/s
# Warmup Iteration   3: 1410.272 ops/s
# Warmup Iteration   4: 1463.457 ops/s
# Warmup Iteration   5: 1489.229 ops/s
Iteration   1: 1489.463 ops/s
Iteration   2: 1512.494 ops/s
Iteration   3: 1523.691 ops/s
Iteration   4: 1464.645 ops/s
Iteration   5: 1403.166 ops/s


Result "org.fufeng.project.base.jmh.JMHSynchronizedTest.add":
  1478.692 ±(99.9%) 184.519 ops/s [Average]
  (min, avg, max) = (1403.166, 1478.692, 1523.691), stdev = 47.919
  CI (99.9%): [1294.173, 1663.211] (assumes normal distribution)


# Run complete. Total time: 00:01:46

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                 Mode  Cnt     Score     Error  Units
JMHSynchronizedTest.add  thrpt    5  1478.692 ± 184.519  ops/s

Process finished with exit code 0
 */

/*
round 1

# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: org.fufeng.project.base.jmh.JMHSynchronizedTest.add

# Run progress: 0.00% complete, ETA 00:01:40
# Fork: 1 of 1
# Warmup Iteration   1: 31880.454 ops/s
# Warmup Iteration   2: 29426.686 ops/s
# Warmup Iteration   3: 33237.891 ops/s
# Warmup Iteration   4: 33457.488 ops/s
# Warmup Iteration   5: 33283.860 ops/s
Iteration   1: 33291.699 ops/s
Iteration   2: 33506.323 ops/s
Iteration   3: 33503.518 ops/s
Iteration   4: 33552.511 ops/s
Iteration   5: 33680.319 ops/s


Result "org.fufeng.project.base.jmh.JMHSynchronizedTest.add":
  33506.874 ±(99.9%) 539.358 ops/s [Average]
  (min, avg, max) = (33291.699, 33506.874, 33680.319), stdev = 140.070
  CI (99.9%): [32967.516, 34046.232] (assumes normal distribution)


# Run complete. Total time: 00:01:45

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                 Mode  Cnt      Score     Error  Units
JMHSynchronizedTest.add  thrpt    5  33506.874 ± 539.358  ops/s

Process finished with exit code 0

 */
