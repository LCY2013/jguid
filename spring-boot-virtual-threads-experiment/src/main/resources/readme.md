[感谢原作者提供思路](https://github.com/mp911de/spring-boot-virtual-threads-experiment)

### 操作流程如下：
```
1、Clone this repository.

2、Install Loom EAP build (Java 16). 
   https://jdk.java.net/loom/

3、Install Postgres locally or via Docker 
    ($ docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres). 
    No special schema required as we're Postgres for simulation of select pg_sleep(1).

4、Build and run this project with Maven 
    ($ mvn compile spring-boot:run)
```

### 访问HTTP端点
```
$ curl http://localhost:8080/ -> Returns OK after 1000ms using Thread.sleep(…). 
    This should simulate a blocking call within the JVM.

$ curl http://localhost:8080/sql -> Returns [{pg_sleep=}] after 1000ms using Postgres via JDBC to call select pg_sleep(1).
    This simulates blocking I/O over the network. 
    Note that using JDBC is bounded by the connection pool and the pool limits the actual concurrency.
```

### 需要修改的线程相关地方
```
Apache Tomcat 
    (org.apache.tomcat.util.threads.TaskThreadFactory, 
    org.apache.tomcat.util.threads.ThreadPoolExecutor, 
    org.apache.tomcat.util.net.AbstractEndpoint (AcceptorThread), 
    org.apache.tomcat.util.net.NioBlockingSelector.BlockPoller, 
    org.apache.tomcat.util.net.NioEndpoint.Poller)

Spring Boot 
    (org.springframework.boot.autoconfigure.BackgroundPreinitializer, 
    org.springframework.boot.autoconfigure.condition.OnClassCondition.ThreadedOutcomesResolver, 
    org.springframework.boot.web.embedded.tomcat.TomcatWebServer (startDaemonAwaitThread))

HikariCP 
    (com.zaxxer.hikari.util.UtilityElf (ThreadPoolExecutor))
```

### idea中启动
```
1、在项目pom中添加jdk版本依赖至 16
    <maven.compiler.target>16</maven.compiler.target>
    <maven.compiler.source>16</maven.compiler.source>

2、项目启动时添加 jvm 参数信息 -DvirtualThreads=true
```