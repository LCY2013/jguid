jdk1.6 方法区实现是在非堆

jdk1.7 方法区（永久代）实现是在堆

jdk1.8 方法区（元空间）实现是在非堆

在 HotSpot 虚拟机、Java7 版本中已经将永久代的静态变量和运行时常量池转移到了堆中，其余部分则存储在 JVM 的非堆内存中，而 Java8 版本已经将方法区中实现的永久代去掉了，并用元空间（class metadata）代替了之前的永久代，并且元空间的存储位置是本地内存。之前永久代的类的元数据存储在了元空间，永久代的静态变量 variables）以及运行时常量池（runtime constant pool）则跟 Java7 一样，转移到了堆中。

元空间替换永久代?

移除永久代是为了融合 HotSpot JVM 与 JRockit VM 而做出的努力，因为 JRockit 没有永久代，所以不需要配置永久代。永久代内存经常不够用或发生内存溢出，爆出异常 java.lang.OutOfMemoryError:PermGen。这是因为在 JDK1.7 版本中，指定的 PermGen 区大小为 8M，由于PermGen 中类的元数据信息在每次 FullGC 的时候都可能被收集，回收率都偏低，成绩很难令人满意；还有，为 PermGen 分配多大的空间很难确定，PermSize 的大小依赖于很多因素，比如，JVM 加载的 class 总数、常量池的大小和方法的大小等。







