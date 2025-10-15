# JGUID - Java 指南与高级学习项目

[English Documentation](./README.md)

## 📚 项目概述

**JGUID** 是一个全面的 Java 学习和实践仓库，涵盖了从基础到高级的 Java 概念，包括 JDK 机制、字节码操作、并发编程、JVM 内部原理、设计模式和性能优化。本项目旨在为希望深入理解 Java 生态系统的开发者提供完整的学习指南。

## 🎯 项目目标

- 展示 JDK 机制和字节码重组编译
- 提供 Java 核心概念的实践示例
- 探索并发编程模式和最佳实践
- 深入研究 JVM 内部原理和性能调优
- 实现全部 23 种 GoF 设计模式
- 掌握现代 Java 特性，包括虚拟线程
- 性能优化技术和实际应用

## 🏗️ 项目结构

```
jguid/
├── deep-in-java/           # 深入 Java 学习模块（7个阶段）
├── concurrent/             # 并发编程示例和模式
├── jvm/                    # JVM 内部原理和性能优化
├── design-patterns/        # 23 种设计模式实现
├── performance-optimization/ # 性能调优技术
├── java-advanced/          # Java 高级主题
├── common/                 # 通用工具和共享代码
├── coroutine/             # 协程实现
├── spring-boot-virtual-threads-experiment/ # 虚拟线程实验
├── spring-virtual-thread/  # Spring 虚拟线程集成
└── jexpression/           # 表达式求值
```

## 📖 模块详情

### 1. Deep-in-Java（深入Java）

分为 7 个阶段的结构化学习路径，涵盖所有核心 Java 概念：

#### 阶段 01：基础知识
- **函数式编程** (`function-introduce`)
  - 函数式接口和 Lambda 表达式
  - Consumer、Supplier、Function、Predicate
  - Stream API 和函数式设计模式
  - 注解处理

- **泛型** (`generic-introduce`)
  - 泛型类和接口
  - 泛型方法和类型边界
  - 类型擦除和通配符
  - 泛型最佳实践

- **面向对象编程** (`object-introduce`)
  - 类基础和内部类
  - Cloneable 和对象复制
  - 枚举类和接口
  - 不可变对象和不可修改接口
  - 字符串操作

#### 阶段 02：集合与算法
- **算法** (`algorithm-introduce`)
  - 冒泡排序
  - 插入排序
  - 归并排序
  - 快速排序
  - 算法分析和复杂度

- **集合框架** (`collection-introduce`)
  - 基础集合：List、Set、Map
  - 高级集合：ConcurrentHashMap、CopyOnWriteArrayList
  - 集合性能特征
  - 自定义集合实现

- **模块化编程** (`modular-introduce`)
  - Java 9+ 模块系统
  - 模块反射
  - 传递性依赖
  - 未命名模块
  - 服务提供者

#### 阶段 03：并发基础
- **线程与进程** (`thread-process`)
  - 线程生命周期和状态
  - 线程创建和管理
  - 进程 API 和子进程
  - 线程转储和诊断
  - 线程中断处理
  - wait/notify 机制

#### 阶段 04：高级并发
- **并发控制** (`concurrency-introduce`)
  - 同步机制
  - 线程安全编程
  - 竞态条件和死锁

- **内存模型** (`memory-introduce`)
  - Java 内存模型 (JMM)
  - Happens-before 关系
  - Synchronized-with 关系
  - AbstractQueuedSynchronizer (AQS)

#### 阶段 05：类加载
- **类加载器** (`classloader-introduce`)
  - 类加载机制
  - ClassLoader 层次结构
  - 自定义 ClassLoader 实现
  - 类加载生命周期
  - ClassPath 和类解析

#### 阶段 06：反射与内省
- **反射** (`reflection-introduce`)
  - 核心反射 API
  - Java 注解
  - 动态代理
  - 字节码插桩
  - JavaBeans 内省
  - 属性编辑器和自定义
  - 事件处理
  - 持久化机制

#### 阶段 07：I/O 操作
- **I/O 系统** (`io-introduce`)
  - 传统 I/O (java.io)
  - 新 I/O (NIO)
  - 文件系统
  - NIO.2 和现代文件操作
  - 缓冲区和通道

### 2. 并发编程模块

全面的并发编程指南，包含实际案例：

#### 核心概念
- **并发处理思路**
  - 分发、同步、互斥
  - 线程协调策略

#### 死锁管理
- **死锁的四个条件**
  1. 互斥：竞争资源只能被某一个线程持有
  2. 占有且等待：线程占有资源同时等待其他资源
  3. 不可抢占：线程占有资源时，其他线程不能抢占
  4. 循环等待：存在线程间的循环等待资源

- **死锁解决策略**
  1. 一次性申请所有资源（消除占有且等待）
  2. 资源抢占（非阻塞锁）
  3. 资源排序（防止循环等待）

#### 等待-通知机制
- synchronized 关键字集成
- wait/notify/notifyAll 模式
- JVM 中的管程实现
- 队列协作（等待队列 + 锁队列）

#### 线程最佳实践
- **安全性问题**：原子性、可见性、有序性
- **活跃性问题**：死锁、活锁、饥饿
- **性能优化**
  - 锁粒度优化
  - 阿姆达尔定律应用
  - 性能指标：吞吐量、延迟、并发量

#### 管程（Monitor）实现
- MESA 模型实现
- Hoare 模型 vs Hasen 模型比较
- wait() 的正确使用姿势
- notify() vs notifyAll() 使用指南

#### 线程生命周期
- 五态模型：初始、可运行、运行、阻塞、终止
- Java 线程状态：NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED
- 线程中断和终止
- 使用 jstack 进行线程诊断

#### 最佳线程数计算
```
单核 CPU：最佳线程数 = 1 + (I/O 时间 / CPU 时间)
多核 CPU：最佳线程数 = CPU 核数 × (1 + I/O 时间 / CPU 时间)
```

#### 并发设计模式
1. **不可变模式** (Immutability Pattern)
2. **写时复制模式** (Copy-on-Write Pattern)
3. **线程本地存储模式** (Thread-Local Storage Pattern)
4. **Guarded Suspension 模式**
5. **Balking 模式**
6. **Thread-Per-Message 模式**
7. **Worker Thread 模式**
8. **生产者-消费者模式** (Producer-Consumer Pattern)

#### 高级主题
- Actor 模型
- 软件事务内存 (STM)
- 多版本并发控制 (MVCC)
- 结构化并发
- CSP（通信顺序进程）

### 3. JVM 模块

深入 JVM 内部原理和性能调优：

#### 字节码技术
- **字节码指令**
  - 栈操作指令
  - 程序流程控制
  - 对象操作和方法调用
  - 算术运算和类型转换

- **方法调用**
  - `invokestatic`：静态方法调用
  - `invokespecial`：构造函数和私有方法
  - `invokevirtual`：实例方法
  - `invokeinterface`：接口方法
  - `invokedynamic`：动态语言支持（Lambda）

#### JVM 运行时结构
- **线程栈**
  - 栈帧
  - 局部变量数组
  - 操作数栈
  - 类引用

- **内存布局**
  - 线程独占：栈、程序计数器
  - 共享：堆、元空间、代码缓存

#### 类加载
- **类的生命周期**
  1. 加载：找 Class 文件
  2. 验证：验证格式、依赖
  3. 准备：静态字段、方法表
  4. 解析：符号解析为引用
  5. 初始化：构造器、静态变量赋值、静态代码块
  6. 使用
  7. 卸载

- **类加载器层次**
  - 启动类加载器 (BootstrapClassLoader)
  - 扩展类加载器 (ExtClassLoader)
  - 应用类加载器 (AppClassLoader)
  - 自定义类加载器

- **加载原则**
  - 双亲委托模型
  - 负责依赖
  - 缓存加载

#### 内存模型
- **堆结构**
  - 年轻代：Eden + 存活区 (S0, S1)
  - 老年代 (Tenured)
  
- **非堆**
  - 元空间 (Metaspace，Java 8+ 替代永久代)
  - 压缩类空间 (CCS)
  - 代码缓存 (Code Cache)

#### JVM 参数
- **系统属性**：`-Dfile.encoding=UTF-8`
- **运行模式**：`-server`、`-client`、`-Xint`、`-Xcomp`、`-Xmixed`
- **内存设置**：
  - `-Xmx`：最大堆内存
  - `-Xms`：初始堆内存
  - `-Xmn`：年轻代大小
  - `-Xss`：线程栈大小
  - `-XX:MaxMetaspaceSize`：元空间限制
  - `-XX:MaxDirectMemorySize`：堆外内存

- **GC 选项**：
  - `-XX:+UseG1GC`：G1 垃圾收集器
  - `-XX:+UseConcMarkSweepGC`：CMS
  - `-XX:+UseSerialGC`：串行 GC
  - `-XX:+UseParallelGC`：并行 GC
  - `-XX:+UseZGC`：ZGC (Java 11+)
  - `-XX:+UseShenandoahGC`：Shenandoah (Java 12+)

- **诊断选项**：
  - `-XX:+HeapDumpOnOutOfMemoryError`
  - `-XX:HeapDumpPath`
  - `-XX:OnError`
  - `-Xdebug -Xrunjdwp`：远程调试

- **JavaAgent**：`-javaagent:jarpath[=options]`

#### 性能优化资源
`jvm/src/main/resources/性能优化文档` 目录包含以下详细文档：
- GC 调优与分析
- JIT 编译优化
- JMM（Java 内存模型）
- 内存分配策略
- 锁优化（Synchronized、Lock）
- 线程上下文切换
- MySQL 优化（索引、事务、死锁）
- InnoDB 架构
- I/O 线程调优
- TCP 网络优化
- Stream API 优化
- 序列化技术
- 集合性能
- 队列实现
- 数据库分库分表

### 4. 设计模式模块

完整实现全部 23 种 GoF 设计模式，并附详细说明：

#### 七大设计原则
1. **开闭原则** (Open-Closed Principle)
   - 对扩展开放，对修改关闭
   - 用抽象构建框架
   - 提高系统可扩展性，降低维护成本

2. **依赖倒置原则** (Dependency Inversion Principle)
   - 高层模块不应依赖低层模块
   - 两者都应依赖抽象
   - 抽象不应依赖细节

3. **单一职责原则** (Single Responsibility Principle)
   - 一个类应该只有一个引起变化的原因
   - 一个类只负责一项职责
   - 降低类的复杂度，提高可维护性

4. **接口隔离原则** (Interface Segregation Principle)
   - 使用多个专门的接口，而不是单一的总接口
   - 客户端不应依赖它不需要的接口
   - 高内聚，低耦合

5. **迪米特法则** (Law of Demeter)
   - 最少知道原则
   - 一个对象应该对其他对象保持最少的了解
   - 只与直接的朋友通信

6. **里氏替换原则** (Liskov Substitution Principle)
   - 子类可以扩展父类的功能，但不能改变父类原有的功能
   - 任何基类可以出现的地方，子类一定可以出现
   - 实现多态性

7. **合成复用原则** (Composite Reuse Principle)
   - 尽量使用对象组合，而不是继承
   - 提高灵活性，降低耦合度

#### 创建型模式（5种）

##### 1. 简单工厂模式
- **定义**：由工厂类决定创建哪种产品类的实例
- **适用场景**：对象类型较少，创建逻辑简单
- **优点**：简单易用，封装创建逻辑
- **缺点**：增加产品时违背开闭原则

##### 2. 工厂方法模式
- **定义**：定义创建对象的接口，让子类决定实例化哪个类
- **适用场景**：
  - 创建对象需要大量重复代码
  - 客户端不依赖产品类实例如何被创建
- **优点**：符合开闭原则，提高可扩展性
- **缺点**：类的个数增加

##### 3. 抽象工厂模式
- **定义**：提供创建一系列相关或相互依赖对象的接口
- **适用场景**：
  - 客户端不依赖产品类实例如何被创建
  - 强调一系列相关产品对象一起使用
- **优点**：具体产品在应用层代码隔离
- **缺点**：扩展产品族困难

##### 4. 建造者模式
- **定义**：将复杂对象的构建与其表示分离
- **适用场景**：
  - 需要生成的对象具有复杂的内部结构
  - 把复杂对象的创建和使用分离
- **优点**：封装性好，扩展性好
- **缺点**：产生多余的 Builder 对象

##### 5. 单例模式
- **定义**：保证一个类仅有一个实例，并提供全局访问点
- **适用场景**：确保任何情况下都只有一个实例
- **优点**：节省内存资源，严格控制访问
- **缺点**：没有接口，扩展困难
- **实现要点**：
  - 私有构造器
  - 线程安全
  - 延迟加载
  - 序列化安全
  - 反射防护

##### 6. 原型模式
- **定义**：通过拷贝原型实例创建新对象
- **适用场景**：
  - 类初始化消耗较多资源
  - 对象创建过程繁琐
  - 循环体中生产大量对象
- **优点**：性能高，简化创建过程
- **缺点**：需要配备克隆方法，深浅拷贝要运用得当

#### 结构型模式（7种）

##### 1. 外观模式（门面模式）
- **定义**：提供统一接口，用来访问子系统中的一组接口
- **适用场景**：
  - 简化复杂子系统
  - 构建多层系统结构
- **优点**：简化调用，减少依赖，符合迪米特法则
- **缺点**：子系统扩展时不符合开闭原则

##### 2. 装饰者模式
- **定义**：在不改变原有对象的基础上，动态地给对象添加功能
- **适用场景**：
  - 扩展一个类的功能
  - 动态增加功能，动态撤销
- **优点**：比继承灵活，符合开闭原则
- **缺点**：更多的类，多层装饰时更复杂

##### 3. 适配器模式
- **定义**：将一个类的接口转换成客户期望的另一个接口
- **类型**：对象适配器、类适配器
- **适用场景**：
  - 已存在的类方法和需求不匹配
  - 维护阶段的兼容性问题
- **优点**：提高类的复用性，符合开闭原则
- **缺点**：增加系统复杂度

##### 4. 享元模式
- **定义**：运用共享技术有效支持大量细粒度的对象
- **适用场景**：
  - 系统性能优化
  - 大量相似对象
- **优点**：减少内存占用，提高效率
- **缺点**：内外部状态管理复杂，需注意线程安全

##### 5. 组合模式
- **定义**：将对象组合成树形结构以表示"部分-整体"的层次结构
- **适用场景**：
  - 树形结构
  - 忽略组合对象与单个对象的差异
- **优点**：清楚定义层次，简化客户端代码，符合开闭原则
- **缺点**：限制类型时较为复杂

##### 6. 桥接模式
- **定义**：将抽象部分与实现部分分离
- **适用场景**：
  - 多个独立变化的维度
  - 避免多层继承导致类爆炸
- **优点**：提高可扩展性，符合开闭和合成复用原则
- **缺点**：增加设计复杂度

##### 7. 代理模式
- **定义**：为对象提供代理以便控制对它的访问
- **类型**：静态代理、动态代理、CGLib 代理
- **适用场景**：
  - 保护目标对象
  - 增强目标对象
- **优点**：代理对象与目标对象分离，降低耦合度
- **缺点**：类的数目增加，可能影响性能

#### 行为型模式（11种）

##### 1. 模版方法模式
- **定义**：定义算法骨架，将一些步骤延迟到子类
- **适用场景**：
  - 一次性实现算法的不变部分
  - 各子类中公共行为被提取出来
- **优点**：封装不变部分，扩展可变部分，符合开闭原则
- **缺点**：每个实现都需要一个子类

##### 2. 迭代器模式
- **定义**：提供方法顺序访问聚合对象中的各个元素
- **适用场景**：
  - 访问聚合对象而无需暴露内部表示
  - 为不同集合结构提供统一接口
- **优点**：分离了集合对象的遍历行为
- **缺点**：为不同集合产生很多子类

##### 3. 策略模式
- **定义**：定义一系列算法，把它们封装起来，并使它们可相互替换
- **适用场景**：
  - 系统有很多类，区别仅在行为不同
  - 系统需要在几种算法中动态选择
- **优点**：符合开闭原则，避免多重条件语句
- **缺点**：客户端需要知道所有策略类

##### 4. 解释器模式
- **定义**：给定一个语言，定义它的文法表示和解释器
- **适用场景**：特定类型问题发生频率足够高
- **优点**：容易改变及扩展文法
- **缺点**：文法规则数目太多时增加复杂度

##### 5. 观察者模式
- **定义**：定义对象间一对多的依赖关系，状态改变时自动更新
- **适用场景**：关联行为场景，建立触发机制
- **优点**：抽象耦合，支持广播通信
- **缺点**：观察者过多影响性能，避免循环调用

##### 6. 备忘录模式
- **定义**：在不破坏封装性的前提下，捕获并保存对象的内部状态
- **适用场景**：
  - 保存及恢复数据相关业务
  - 后悔机制（撤销操作）
- **优点**：提供恢复机制，封装存档信息
- **缺点**：资源占用

##### 7. 命令模式
- **定义**：将请求封装为对象
- **适用场景**：
  - 请求调用者和接收者需要解耦
  - 需要抽象出等待执行的行为
- **优点**：降低耦合度，容易扩展新命令
- **缺点**：命令的无限扩展会增加类的数量

##### 8. 中介者模式
- **定义**：用中介对象封装一系列的对象交互
- **适用场景**：
  - 对象之间存在复杂的依赖关系
  - 交互的公共行为需要改变
- **优点**：将一对多转换成一对一，降低耦合
- **缺点**：中介者过于复杂

##### 9. 责任链模式
- **定义**：使多个对象都有机会处理请求，避免发送者和接收者的耦合
- **适用场景**：多个对象可以处理同一请求
- **优点**：请求发送者和接收者解耦，责任链可动态组合
- **缺点**：责任链太长影响性能

##### 10. 访问者模式
- **定义**：将数据结构与数据操作分离
- **适用场景**：
  - 数据结构相对稳定，操作经常变化
  - 数据结构包含很多类型对象
- **优点**：增加新的操作很容易
- **缺点**：增加新的数据结构很困难

##### 11. 状态模式
- **定义**：允许对象在内部状态改变时改变其行为
- **适用场景**：对象存在多个状态且可相互转换
- **优点**：将不同的状态隔离，易于增加新状态
- **缺点**：状态多时导致类数目增加

### 5. 性能优化模块

实际性能调优技术和工具：

#### 重点领域
- JVM 性能调优
- 垃圾收集优化
- 内存泄漏检测和解决
- 线程池调优
- 数据库查询优化
- 缓存策略
- 网络 I/O 优化

#### 工具和技术
- 性能分析工具
- 性能监控
- 瓶颈识别
- 负载测试
- 基准测试开发

### 6. Java 高级模块

高级 Java 编程主题：

- 字节码操作
- 高级类加载技术
- JVM 内部深入研究
- 性能分析
- 内存分析

### 7. 虚拟线程模块

探索 Project Loom 和虚拟线程：

#### spring-boot-virtual-threads-experiment
- Spring Boot 与虚拟线程集成
- 使用 Loom 的 Servlet 实现
- 性能对比
- 最佳实践

#### spring-virtual-thread
- Spring Framework 虚拟线程支持
- 虚拟线程异步处理
- 数据库连接处理

### 8. 其他模块

#### Common 模块
- 共享工具
- 通用抽象
- 辅助类

#### Coroutine 模块
- Java 中的协程实现
- 异步编程模式

#### JExpression 模块
- 表达式求值
- 动态表达式解析

## 🛠️ 使用的技术

- **Java 版本**：JDK 8+
- **构建工具**：Maven 3.x
- **Spring Boot**：3.2.4
- **主要库**：
  - JUnit 测试框架
  - SLF4J/Logback 日志框架
  - 各种 Apache Commons 库

## 🚀 快速开始

### 前置要求

- JDK 8 或更高版本（部分模块推荐 JDK 11+）
- Maven 3.6+
- IDE（IntelliJ IDEA、Eclipse 或 VS Code）

### 构建项目

```bash
# 克隆仓库
git clone https://github.com/yourusername/jguid.git
cd jguid

# 构建所有模块
mvn clean install

# 构建特定模块
cd deep-in-java
mvn clean install
```

### 运行示例

每个模块都包含可运行的示例，带有 main 方法。导航到特定模块并运行：

```bash
# 示例：运行设计模式演示
cd design-patterns
mvn clean compile
mvn exec:java -Dexec.mainClass="org.fufeng.design.creational.factory.SimpleFactoryDemo"
```

## 📝 推荐学习路径

### 初级路径
1. 从 `deep-in-java/stage01` 开始（函数式编程、泛型、对象）
2. 进入 `deep-in-java/stage02`（集合、算法）
3. 学习 `design-patterns` 基础
4. 使用 `common` 模块工具实践

### 中级路径
1. 深入学习 `deep-in-java/stage03-04`（并发）
2. 探索 `concurrent` 模块示例
3. 学习 `design-patterns` 中的所有模式
4. 学习 `deep-in-java/stage05-06`（类加载器、反射）

### 高级路径
1. 掌握 `jvm` 内部原理和优化
2. 学习 `performance-optimization` 技术
3. 探索 `java-advanced` 字节码操作
4. 实验 `spring-virtual-thread` 和现代特性
5. 复习性能优化文档

## 📚 文档

每个模块的详细文档可在各自目录中找到：

- `/concurrent/src/main/resources/并发编程.md` - 并发编程指南
- `/design-patterns/README.md` - 设计模式详细说明
- `/java-advanced/src/main/resources/JVM进阶.md` - JVM 高级主题
- `/jvm/src/main/resources/性能优化文档/` - 性能优化指南

## 🤝 贡献

欢迎贡献！请随时提交问题和拉取请求。

### 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 详见 LICENSE 文件。

## 👨‍💻 作者

**LCY（罗春云）**
- GitHub: [@LCY2013](https://github.com/LCY2013)

## 🙏 致谢

- Oracle Java 官方文档
- 《Effective Java》 by Joshua Bloch
- 《Java 并发编程实战》
- 《设计模式：可复用面向对象软件的基础》
- HikariCP 并发编程启发
- LMAX Disruptor 高性能模式

## 📖 参考资料

- [HikariCP Down the Rabbit Hole](https://github.com/brettwooldridge/HikariCP/wiki/Down-the-Rabbit-Hole)
- [LMAX Disruptor](https://github.com/LMAX-Exchange/disruptor/wiki/Introduction)
- [NIO 进化之路](http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf)
- [Multiverse STM](https://github.com/pveentjer/Multiverse)
- [JCSP - Java CSP](https://www.cs.kent.ac.uk/projects/ofa/jcsp/)
- [结构化并发](https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/)

## 🔄 项目状态

这是一个活跃的学习项目，持续更新新的示例和改进。

---

**编码愉快！🎉**

如果您觉得这个项目有帮助，请考虑给它一个 ⭐️！

