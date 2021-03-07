### 字节码、类加载器、虚拟机

#### 字节码技术
Java bytecode 由单字节(byte)的指令组成，理论上最多支持 256 个操作码(opcode)。 实际上 Java 只使用了200左右的操作码， 还有一些操作码则保留给调试操作。

根据指令的性质，主要分为四个大类:
```text
1. 栈操作指令，包括与局部变量交互的指令
 
2. 程序流程控制指令

3. 对象操作指令，包括方法调用指令

4. 算术运算以及类型转换指令
```
![bytecodecompiled](images/bytecodecompiled.png)

![栈上与本地变量表数据转换流程](images/栈上与本地变量表数据转换流程.png)

#### 字节码运行时结构
![字节码运行时](images/字节码运行时.png)
```text
JVM 是一台基于栈的计算机器。 

每个线程都有一个独属于自己的线程栈(JVM Stack)，用于存储栈帧(Frame)。

每一次方法调用，JVM 都会自动创建一个栈帧。 

栈帧由操作数栈， 局部变量数组以及一个 Class 引用组成。
 
Class 引用 指向当前方法在运行时常量池中对应的 Class。
```

#### 助记符到二进制(org.fufeng.jvm.bytecode.demo01)
![助记符到二进制](images/助记符到二进制.png)

#### 四则运行(org.fufeng.jvm.bytecode.demo02)
![数值处理与本地变量表](images/数值处理与本地变量表.png)
![数值处理与本地变量表2](images/数值处理与本地变量表2.png)

#### 算数操作与类型转换
![算数操作符与数据转换指令](images/算数操作符与数据转换指令.png)

#### 循环控制例子(org.fufeng.jvm.bytecode.demo03)
![循环控制指令分析](images/循环控制指令分析.png)

#### 方法调用的指令
```text
invokestatic，顾名思义，这个指令用于调用某个类的静态方法，这是方法调用指令中最 快的一个。

invokespecial, 用来调用构造函数，但也可以用于调用同一个类中的 private 方法, 以及 可见的超类方法。

invokevirtual，如果是具体类型的目标对象，invokevirtual 用于调用公共，受保护和 package 级的私有方法。

invokeinterface，当通过接口引用来调用方法时，将会编译为 invokeinterface 指令。

invokedynamic，JDK7 新增加的指令，是实现“动态类型语言”(Dynamically Typed Language)支持而进行的升级改进，同时也是 JDK8 以后支持 lambda 表达式的实现基础。
```

#### 例子
![常量栈本地变量表一次执行](images/常量栈本地变量表一次执行.png)

### JVM 类加载器

#### 类的生命周期
![类的生命周期](images/类的生命周期.png)
```text
1. 加载(Loading):找 Class 文件
2. 验证(Verification):验证格式、依赖
3. 准备(Preparation):静态字段、方法表
4. 解析(Resolution):符号解析为引用
5. 初始化(Initialization):构造器、静态变 量赋值、静态代码块
6. 使用(Using)
7. 卸载(Unloading)
```

#### 类的加载时机



















