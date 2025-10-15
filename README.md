# JGUID - Java Guide and Advanced Learning Project

[中文文档](./README_CN.md)

## 📚 Project Overview

**JGUID** is a comprehensive Java learning and practice repository that covers fundamental to advanced Java concepts, including JDK mechanisms, bytecode manipulation, concurrent programming, JVM internals, design patterns, and performance optimization. This project serves as a complete guide for Java developers looking to deepen their understanding of the Java ecosystem.

## 🎯 Project Objectives

- Demonstrate JDK mechanisms and bytecode recompilation
- Provide hands-on examples for Java core concepts
- Explore concurrent programming patterns and best practices
- Deep dive into JVM internals and performance tuning
- Implement all 23 Gang of Four design patterns
- Master modern Java features including virtual threads
- Performance optimization techniques and real-world applications

## 🏗️ Project Structure

```
jguid/
├── deep-in-java/           # In-depth Java learning modules (7 stages)
├── concurrent/             # Concurrent programming examples and patterns
├── jvm/                    # JVM internals and performance optimization
├── design-patterns/        # 23 Design Patterns implementation
├── performance-optimization/ # Performance tuning techniques
├── java-advanced/          # Advanced Java topics
├── common/                 # Common utilities and shared code
├── coroutine/             # Coroutine implementations
├── spring-boot-virtual-threads-experiment/ # Virtual threads experiments
├── spring-virtual-thread/  # Spring virtual thread integration
└── jexpression/           # Expression evaluation
```

## 📖 Module Details

### 1. Deep-in-Java (深入Java)

A structured learning path divided into 7 stages covering all essential Java concepts:

#### Stage 01: Fundamentals
- **Function Programming** (`function-introduce`)
  - Functional interfaces and lambda expressions
  - Consumer, Supplier, Function, Predicate
  - Stream API and functional design patterns
  - Annotation processing

- **Generics** (`generic-introduce`)
  - Generic classes and interfaces
  - Generic methods and type bounds
  - Type erasure and wildcards
  - Generic best practices

- **Object-Oriented Programming** (`object-introduce`)
  - Class fundamentals and inner classes
  - Cloneable and object copying
  - Enum classes and interfaces
  - Immutable objects and unmodifiable interfaces
  - String manipulation

#### Stage 02: Collections and Algorithms
- **Algorithms** (`algorithm-introduce`)
  - Bubble Sort
  - Insertion Sort
  - Merge Sort
  - Quick Sort
  - Algorithm analysis and complexity

- **Collections Framework** (`collection-introduce`)
  - Basic collections: List, Set, Map
  - Advanced collections: ConcurrentHashMap, CopyOnWriteArrayList
  - Collection performance characteristics
  - Custom collection implementations

- **Modular Programming** (`modular-introduce`)
  - Java 9+ module system
  - Module reflection
  - Transitive requires
  - Unnamed modules
  - Service providers

#### Stage 03: Concurrency Fundamentals
- **Thread and Process** (`thread-process`)
  - Thread lifecycle and states
  - Thread creation and management
  - Process API and child processes
  - Thread dumps and diagnostics
  - Thread interruption handling
  - Wait/notify mechanisms

#### Stage 04: Advanced Concurrency
- **Concurrency Control** (`concurrency-introduce`)
  - Synchronization mechanisms
  - Thread-safe programming
  - Race conditions and deadlocks

- **Memory Model** (`memory-introduce`)
  - Java Memory Model (JMM)
  - Happens-before relationships
  - Synchronized-with relations
  - AbstractQueuedSynchronizer (AQS)

#### Stage 05: Class Loading
- **ClassLoader** (`classloader-introduce`)
  - Class loading mechanisms
  - ClassLoader hierarchy
  - Custom ClassLoader implementation
  - Class loading lifecycle
  - ClassPath and class resolution

#### Stage 06: Reflection and Introspection
- **Reflection** (`reflection-introduce`)
  - Core reflection APIs
  - Java annotations
  - Dynamic proxy
  - Bytecode instrumentation
  - JavaBeans introspection
  - Property editors and customization
  - Event handling
  - Persistence mechanisms

#### Stage 07: I/O Operations
- **I/O Systems** (`io-introduce`)
  - Traditional I/O (java.io)
  - New I/O (NIO)
  - File systems
  - NIO.2 and modern file operations
  - Buffers and channels

### 2. Concurrent Programming Module

Comprehensive concurrent programming guide with real-world examples:

#### Core Concepts
- **Concurrency Processing Patterns**
  - Distribution, synchronization, and mutual exclusion
  - Thread coordination strategies

#### Deadlock Management
- **Four Conditions of Deadlock**
  1. Mutual Exclusion
  2. Hold and Wait
  3. No Preemption
  4. Circular Wait

- **Deadlock Resolution Strategies**
  1. One-time resource allocation (eliminates hold-and-wait)
  2. Resource preemption (non-blocking locks)
  3. Resource ordering (prevents circular wait)

#### Wait-Notify Mechanism
- Synchronized keyword integration
- Wait/notify/notifyAll patterns
- Monitor implementation in JVM
- Queue coordination (wait queue + lock queue)

#### Threading Best Practices
- **Safety Issues**: Atomicity, visibility, ordering
- **Liveness Issues**: Deadlock, livelock, starvation
- **Performance Optimization**
  - Lock granularity optimization
  - Amdahl's Law application
  - Performance metrics: throughput, latency, concurrency

#### Monitor (管程) Implementation
- MESA model implementation
- Hoare model vs Hasen model comparison
- Proper wait() usage patterns
- notify() vs notifyAll() guidelines

#### Thread Lifecycle
- Five-state model: Initial, Runnable, Running, Blocked, Terminated
- Java thread states: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
- Thread interruption and termination
- Thread diagnostics with jstack

#### Optimal Thread Configuration
```
Single Core CPU: Optimal Threads = 1 + (I/O time / CPU time)
Multi-Core CPU: Optimal Threads = CPU Cores × (1 + I/O time / CPU time)
```

#### Design Patterns for Concurrency
1. **Immutability Pattern**
2. **Copy-on-Write Pattern**
3. **Thread-Local Storage Pattern**
4. **Guarded Suspension Pattern**
5. **Balking Pattern**
6. **Thread-Per-Message Pattern**
7. **Worker Thread Pattern**
8. **Producer-Consumer Pattern**

#### Advanced Topics
- Actor Model
- Software Transactional Memory (STM)
- Multi-Version Concurrency Control (MVCC)
- Structured Concurrency
- CSP (Communicating Sequential Processes)

### 3. JVM Module

Deep dive into JVM internals and performance tuning:

#### Bytecode Technology
- **Bytecode Instructions**
  - Stack operation instructions
  - Program flow control
  - Object operations and method invocation
  - Arithmetic and type conversion

- **Method Invocation**
  - `invokestatic`: Static method calls
  - `invokespecial`: Constructor and private methods
  - `invokevirtual`: Instance methods
  - `invokeinterface`: Interface methods
  - `invokedynamic`: Dynamic language support (Lambda)

#### JVM Runtime Structure
- **Thread Stack**
  - Stack frames
  - Local variable array
  - Operand stack
  - Class references

- **Memory Layout**
  - Thread-local: Stack, PC register
  - Shared: Heap, Metaspace, Code Cache

#### Class Loading
- **Class Lifecycle**
  1. Loading: Locate class file
  2. Verification: Format and dependency validation
  3. Preparation: Static field and method table initialization
  4. Resolution: Symbol resolution to references
  5. Initialization: Constructor, static initialization
  6. Using
  7. Unloading

- **ClassLoader Hierarchy**
  - Bootstrap ClassLoader
  - Extension ClassLoader
  - Application ClassLoader
  - Custom ClassLoaders

- **Loading Principles**
  - Parent delegation model
  - Dependency responsibility
  - Class caching

#### Memory Model
- **Heap Structure**
  - Young Generation: Eden + Survivor (S0, S1)
  - Old Generation (Tenured)
  
- **Non-Heap**
  - Metaspace (replaces PermGen in Java 8+)
  - Compressed Class Space (CCS)
  - Code Cache

#### JVM Parameters
- **System Properties**: `-Dfile.encoding=UTF-8`
- **Runtime Mode**: `-server`, `-client`, `-Xint`, `-Xcomp`, `-Xmixed`
- **Memory Settings**:
  - `-Xmx`: Maximum heap size
  - `-Xms`: Initial heap size
  - `-Xmn`: Young generation size
  - `-Xss`: Thread stack size
  - `-XX:MaxMetaspaceSize`: Metaspace limit
  - `-XX:MaxDirectMemorySize`: Off-heap memory

- **GC Options**:
  - `-XX:+UseG1GC`: G1 garbage collector
  - `-XX:+UseConcMarkSweepGC`: CMS
  - `-XX:+UseSerialGC`: Serial GC
  - `-XX:+UseParallelGC`: Parallel GC
  - `-XX:+UseZGC`: ZGC (Java 11+)
  - `-XX:+UseShenandoahGC`: Shenandoah (Java 12+)

- **Diagnostics**:
  - `-XX:+HeapDumpOnOutOfMemoryError`
  - `-XX:HeapDumpPath`
  - `-XX:OnError`
  - `-Xdebug -Xrunjdwp`: Remote debugging

- **JavaAgent**: `-javaagent:jarpath[=options]`

#### Performance Optimization Resources
The `jvm/src/main/resources/性能优化文档` directory contains extensive documentation on:
- GC tuning and analysis
- JIT compilation optimization
- JMM (Java Memory Model)
- Memory allocation strategies
- Lock optimization (Synchronized, Lock)
- Thread context switching
- MySQL optimization (indexes, transactions, deadlocks)
- InnoDB architecture
- I/O thread tuning
- TCP network optimization
- Stream API optimization
- Serialization techniques
- Collection performance
- Queue implementations
- Database sharding

### 4. Design Patterns Module

Complete implementation of all 23 Gang of Four design patterns with detailed explanations:

#### Seven Design Principles
1. **Open-Closed Principle** (开闭原则)
   - Open for extension, closed for modification
   - Use abstraction to build frameworks
   - Improve extensibility, reduce maintenance costs

2. **Dependency Inversion Principle** (依赖倒置原则)
   - High-level modules should not depend on low-level modules
   - Both should depend on abstractions
   - Abstractions should not depend on details

3. **Single Responsibility Principle** (单一职责原则)
   - A class should have only one reason to change
   - Each class should have only one responsibility
   - Reduces complexity, improves maintainability

4. **Interface Segregation Principle** (接口隔离原则)
   - Use multiple specialized interfaces instead of single general interface
   - Clients should not depend on interfaces they don't use
   - High cohesion, low coupling

5. **Law of Demeter** (迪米特法则)
   - Principle of Least Knowledge
   - An object should have minimal knowledge about other objects
   - Only talk to immediate friends

6. **Liskov Substitution Principle** (里氏替换原则)
   - Subtypes must be substitutable for their base types
   - Subclasses can extend but not change parent functionality
   - Enables polymorphism

7. **Composite Reuse Principle** (合成复用原则)
   - Favor composition over inheritance
   - Improves flexibility and reduces coupling

#### Creational Patterns (5)

##### 1. Simple Factory Pattern
- **Definition**: Factory class decides which product instance to create
- **Use Cases**: Few object types, simple creation logic
- **Pros**: Easy to use, encapsulates creation
- **Cons**: Violates Open-Closed Principle when adding products

##### 2. Factory Method Pattern
- **Definition**: Defines interface for creating objects, lets subclasses decide which class to instantiate
- **Use Cases**: 
  - Creation requires repetitive code
  - Client doesn't depend on how products are created
- **Pros**: Follows Open-Closed Principle, improves extensibility
- **Cons**: Increases number of classes

##### 3. Abstract Factory Pattern
- **Definition**: Provides interface for creating families of related objects
- **Use Cases**: 
  - Client independent of product creation details
  - Product families need to be used together
- **Pros**: Isolates concrete implementations
- **Cons**: Difficult to extend product families

##### 4. Builder Pattern
- **Definition**: Separates object construction from representation
- **Use Cases**:
  - Complex objects with many properties
  - Separate creation from usage
- **Pros**: Good encapsulation, flexible construction
- **Cons**: Extra Builder objects, modification costs

##### 5. Singleton Pattern
- **Definition**: Ensures class has only one instance with global access
- **Use Cases**: Need exactly one instance
- **Pros**: Saves memory, controls access
- **Cons**: Hard to extend, no interface
- **Implementation Considerations**:
  - Private constructor
  - Thread safety
  - Lazy loading
  - Serialization safety
  - Reflection protection

##### 6. Prototype Pattern
- **Definition**: Create objects by copying prototype instances
- **Use Cases**:
  - Initialization is resource-intensive
  - Complex object creation
  - Bulk object creation in loops
- **Pros**: Better performance than `new`, simplifies creation
- **Cons**: Requires clone methods, deep vs shallow copy complexity

#### Structural Patterns (7)

##### 1. Facade Pattern (门面模式)
- **Definition**: Provides unified interface to subsystem interfaces
- **Use Cases**:
  - Simplify complex subsystems
  - Multi-layer system structure
- **Pros**: Simplifies calls, reduces dependencies, follows Law of Demeter
- **Cons**: Doesn't follow Open-Closed Principle for subsystem changes

##### 2. Decorator Pattern (装饰者模式)
- **Definition**: Attaches additional responsibilities to objects dynamically
- **Use Cases**:
  - Extend class functionality
  - Dynamic feature addition/removal
- **Pros**: More flexible than inheritance, follows Open-Closed Principle
- **Cons**: More classes, complexity in multi-layer decoration

##### 3. Adapter Pattern (适配器模式)
- **Definition**: Converts interface to expected interface
- **Types**: Object Adapter, Class Adapter
- **Use Cases**:
  - Existing class methods don't match requirements
  - Maintenance phase compatibility
- **Pros**: Improves reusability, follows Open-Closed Principle
- **Cons**: Increases complexity

##### 4. Flyweight Pattern (享元模式)
- **Definition**: Shares objects to support large numbers efficiently
- **Use Cases**:
  - System performance optimization
  - Large number of similar objects
- **Pros**: Reduces memory usage, improves efficiency
- **Cons**: Complexity in managing internal/external state, thread safety

##### 5. Composite Pattern (组合模式)
- **Definition**: Composes objects into tree structures for part-whole hierarchies
- **Use Cases**:
  - Tree structures
  - Ignore difference between composite and individual objects
- **Pros**: Clear hierarchy, simplifies client code, follows Open-Closed Principle
- **Cons**: Complex type restrictions, abstract design

##### 6. Bridge Pattern (桥接模式)
- **Definition**: Separates abstraction from implementation
- **Use Cases**:
  - Multiple independent varying dimensions
  - Avoid multi-level inheritance explosion
- **Pros**: Improves extensibility, follows Open-Closed and Composite Reuse Principles
- **Cons**: Increased design complexity

##### 7. Proxy Pattern (代理模式)
- **Definition**: Provides surrogate to control access to object
- **Types**: Static Proxy, Dynamic Proxy, CGLib Proxy
- **Use Cases**:
  - Protect target object
  - Enhance target object
- **Pros**: Separates proxy from target, reduces coupling
- **Cons**: More classes, potential performance impact

#### Behavioral Patterns (11)

##### 1. Template Method Pattern (模版方法模式)
- **Definition**: Defines algorithm skeleton, lets subclasses override steps
- **Use Cases**:
  - Implement invariant algorithm parts
  - Extract common behavior to parent class
- **Pros**: Encapsulates invariant, extends variant parts, follows Open-Closed Principle
- **Cons**: Each implementation needs subclass

##### 2. Iterator Pattern (迭代器模式)
- **Definition**: Sequential access to aggregate elements without exposing representation
- **Use Cases**:
  - Access aggregate without exposing internals
  - Unified interface for different collections
- **Pros**: Separates traversal behavior
- **Cons**: Many subclasses for different structures

##### 3. Strategy Pattern (策略模式)
- **Definition**: Encapsulates algorithms, makes them interchangeable
- **Use Cases**:
  - Classes differ only in behavior
  - Dynamic algorithm switching
- **Pros**: Open-Closed Principle, avoids conditionals, security
- **Cons**: Client must know strategies, many strategy classes

##### 4. Interpreter Pattern (解释器模式)
- **Definition**: Defines grammar representation and interpreter
- **Use Cases**: High-frequency specific problems
- **Pros**: Easy to change and extend grammar
- **Cons**: Complex for large grammars

##### 5. Observer Pattern (观察者模式)
- **Definition**: One-to-many dependency, automatic update on state change
- **Use Cases**: Associated behavior scenarios, trigger mechanisms
- **Pros**: Abstract coupling, supports broadcast
- **Cons**: Too many observers impact performance, avoid circular calls

##### 6. Memento Pattern (备忘录模式)
- **Definition**: Captures and externalizes internal state for later restoration
- **Use Cases**:
  - Save/restore data
  - Undo mechanisms
- **Pros**: Provides restoration mechanism, encapsulates state
- **Cons**: Resource consumption

##### 7. Command Pattern (命令模式)
- **Definition**: Encapsulates request as object
- **Use Cases**:
  - Decouple invoker and receiver
  - Abstract behavior for execution
- **Pros**: Reduces coupling, easy to extend commands
- **Cons**: Many command classes

##### 8. Mediator Pattern (中介者模式)
- **Definition**: Encapsulates object interactions in mediator
- **Use Cases**:
  - Complex dependencies between objects
  - Centralize interaction behavior
- **Pros**: Converts one-to-many to one-to-one, reduces coupling
- **Cons**: Mediator can become complex

##### 9. Chain of Responsibility Pattern (责任链模式)
- **Definition**: Gives multiple objects chance to handle request
- **Use Cases**: Multiple handlers for same request
- **Pros**: Decouples sender and receiver, dynamic composition
- **Cons**: Performance impact if chain is too long

##### 10. Visitor Pattern (访问者模式)
- **Definition**: Separates data structure from operations
- **Use Cases**:
  - Stable data structure, varying operations
  - Data structures contain many object types
- **Pros**: Easy to add operations
- **Cons**: Hard to add new elements

##### 11. State Pattern (状态模式)
- **Definition**: Changes behavior when internal state changes
- **Use Cases**: Object has multiple states with different behaviors
- **Pros**: Encapsulates states, easy to add states
- **Cons**: Many state classes for complex scenarios

### 5. Performance Optimization Module

Real-world performance tuning techniques and tools:

#### Focus Areas
- JVM performance tuning
- Garbage collection optimization
- Memory leak detection and resolution
- Thread pool tuning
- Database query optimization
- Caching strategies
- Network I/O optimization

#### Tools and Techniques
- Profiling tools
- Performance monitoring
- Bottleneck identification
- Load testing
- Benchmark development

### 6. Java Advanced Module

Advanced Java programming topics:

- Bytecode manipulation
- Advanced class loading techniques
- JVM internals deep dive
- Performance profiling
- Memory analysis

### 7. Virtual Threads Modules

Exploration of Project Loom and virtual threads:

#### spring-boot-virtual-threads-experiment
- Spring Boot integration with virtual threads
- Servlet implementation using Loom
- Performance comparisons
- Best practices

#### spring-virtual-thread
- Spring Framework virtual thread support
- Async processing with virtual threads
- Database connection handling

### 8. Other Modules

#### Common Module
- Shared utilities
- Common abstractions
- Helper classes

#### Coroutine Module
- Coroutine implementations in Java
- Asynchronous programming patterns

#### JExpression Module
- Expression evaluation
- Dynamic expression parsing

## 🛠️ Technologies Used

- **Java Version**: JDK 8+
- **Build Tool**: Maven 3.x
- **Spring Boot**: 3.2.4
- **Key Libraries**:
  - JUnit for testing
  - SLF4J/Logback for logging
  - Various Apache Commons libraries

## 🚀 Getting Started

### Prerequisites

- JDK 8 or higher (JDK 11+ recommended for some modules)
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Build the Project

```bash
# Clone the repository
git clone https://github.com/yourusername/jguid.git
cd jguid

# Build all modules
mvn clean install

# Build specific module
cd deep-in-java
mvn clean install
```

### Run Examples

Each module contains runnable examples with main methods. Navigate to specific module and run:

```bash
# Example: Run a design pattern demo
cd design-patterns
mvn clean compile
mvn exec:java -Dexec.mainClass="org.fufeng.design.creational.factory.SimpleFactoryDemo"
```

## 📝 Learning Path Recommendation

### Beginner Path
1. Start with `deep-in-java/stage01` (Functions, Generics, Objects)
2. Move to `deep-in-java/stage02` (Collections, Algorithms)
3. Study `design-patterns` fundamentals
4. Practice with `common` module utilities

### Intermediate Path
1. Deep dive into `deep-in-java/stage03-04` (Concurrency)
2. Explore `concurrent` module examples
3. Study all design patterns in `design-patterns`
4. Learn `deep-in-java/stage05-06` (ClassLoader, Reflection)

### Advanced Path
1. Master `jvm` internals and optimization
2. Study `performance-optimization` techniques
3. Explore `java-advanced` bytecode manipulation
4. Experiment with `spring-virtual-thread` and modern features
5. Review performance optimization documentation

## 📚 Documentation

Detailed documentation for each module can be found in respective directories:

- `/concurrent/src/main/resources/并发编程.md` - Concurrent programming guide
- `/design-patterns/README.md` - Design patterns详细说明
- `/java-advanced/src/main/resources/JVM进阶.md` - Advanced JVM topics
- `/jvm/src/main/resources/性能优化文档/` - Performance optimization guides

## 🤝 Contributing

Contributions are welcome! Please feel free to submit issues and pull requests.

### Contribution Guidelines

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

**LCY (LuoChunYun)**
- GitHub: [@LCY2013](https://github.com/LCY2013)

## 🙏 Acknowledgments

- Oracle Java Documentation
- Effective Java by Joshua Bloch
- Java Concurrency in Practice
- Design Patterns: Elements of Reusable Object-Oriented Software
- HikariCP for concurrent programming inspiration
- LMAX Disruptor for high-performance patterns

## 📖 References

- [HikariCP Down the Rabbit Hole](https://github.com/brettwooldridge/HikariCP/wiki/Down-the-Rabbit-Hole)
- [LMAX Disruptor](https://github.com/LMAX-Exchange/disruptor/wiki/Introduction)
- [NIO Evolution](http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf)
- [Multiverse STM](https://github.com/pveentjer/Multiverse)
- [JCSP - Java CSP](https://www.cs.kent.ac.uk/projects/ofa/jcsp/)
- [Structured Concurrency](https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/)

## 🔄 Project Status

This is an active learning project, continuously updated with new examples and improvements.

---

**Happy Coding! 🎉**

If you find this project helpful, please consider giving it a ⭐️!
