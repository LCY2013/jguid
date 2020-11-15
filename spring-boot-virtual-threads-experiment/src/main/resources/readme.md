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