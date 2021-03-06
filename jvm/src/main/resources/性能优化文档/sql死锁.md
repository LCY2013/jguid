### 数据库死锁模拟

```sql
CREATE TABLE `order_record`  (  
    `id` int(11) NOT NULL AUTO_INCREMENT,  
    `order_no` int(11) DEFAULT NULL,  
    `status` int(4) DEFAULT NULL,  
    `create_date` datetime(0) DEFAULT NULL,  
    PRIMARY KEY (`id`) USING BTREE,  
    INDEX `idx_order_status`(`order_no`,`status`) USING BTREE
) ENGINE = InnoDB
```

MySQL 数据库和Oracle 提交事务不太一样，MySQL 数据库默认情况下是自动提交事务，可以通过以下命令行查看自动提交事务是否开启：

mysql> show variables like 'autocommit';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| autocommit    | ON    |
+---------------+-------+
1 row in set (0.01 sec)

mysql> set autocommit = 0;
Query OK, 0 rows affected (0.01 sec)

订单在做幂等性校验时，先是通过订单号检查订单是否存在，如果不存在则新增订单记录。知道具体的逻辑之后，我们再来模拟创建产生死锁的运行 SQL 语句。首先，我们模拟新建两个订单，并按照以下顺序执行幂等性校验 SQL 语句（垂直方向代表执行的时间顺序）：

![两个事务流程](images/两个事务.png)
```text
# 1、检查订单号为4的订单是否存在
BEGIN;
SELECT id FROM `order_record` WHERE `order_no` = 4 for update;

# 2、查询订单号为5的订单是否存在
BEGIN;
SELECT id FROM `order_record` WHERE `order_no` = 5 for update;

# 3、没有就插入订单号为4的订单信息
INSERT INTO `order_record`(`order_no`,`status`,`create_date`) VALUES(4,1,'2021-02-02 12:12:12');

# 4、没有就插入订单号为5的订单信息
INSERT INTO `order_record`(`order_no`,`status`,`create_date`) VALUES(5,1,'2021-02-02 12:12:12');

# 提交
COMMIT;

# 提交
COMMIT;
```
此时，我们会发现两个事务已经进入死锁状态。我们可以在 information_schema 数据库中查询到具体的死锁情况，如下所示：
```text
mysql> use information_schema;
Database changed

mysql> select * from innodb_lock_waits;

mysql> select * from innodb_locks;
```
看到这，你可能会想，为什么 SELECT 要加 for update 排他锁，而不是使用共享锁呢？试想下，如果是两个订单号一样的请求同时进来，就有可能出现幻读。也就是说，一开始事务A 中的查询没有该订单号，后来事务 B 新增了一个该订单号的记录，此时事务 A 再新增一条该订单号记录，就会创建重复的订单记录。面对这种情况，我们可以使用锁间隙算法来防止幻读。

### 死锁是如何产生的？
行锁的具体实现算法有三种：record lock、gap lock 以及 next-key lock。record lock是专门对索引项加锁；gap lock 是对索引项之间的间隙加锁；next-key lock 则是前面两种的组合，对索引项以其之间的间隙加锁。

只在可重复读或以上隔离级别下的特定操作才会取得 gap lock 或 next-key lock，在Select、Update 和 Delete 时，除了基于唯一索引的查询之外，其它索引查询时都会获取gap lock 或 next-key lock，即锁住其扫描的范围。主键索引也属于唯一索引，所以主键索引是不会使用 gap lock 或 next-key lock。

在 MySQL 中，gap lock 默认是开启的，即 innodb_locks_unsafe_for_binlog 参数值是disable 的，且 MySQL 中默认的是 RR 事务隔离级别。

当我们执行以下查询 SQL 时，由于 order_no 列为非唯一索引，此时又是 RR 事务隔离级别，所以 SELECT 的加锁类型为 gap lock，这里的 gap 范围是 (4,+∞）。
```text
SELECT id FROM `order_record` where `order_no` = 4 for update;
```
执行查询 SQL 语句获取的 gap lock 并不会导致阻塞，而当我们执行以下插入 SQL 时，会在插入间隙上再次获取插入意向锁。插入意向锁其实也是一种 gap 锁，它与 gap lock 是冲突的，所以当其它事务持有该间隙的 gap lock 时，需要等待其它事务释放 gap lock 之后，才能获取到插入意向锁。

以上事务 A 和事务 B 都持有间隙 (4,+∞）的 gap 锁，而接下来的插入操作为了获取到插入意向锁，都在等待对方事务的 gap 锁释放，于是就造成了循环等待，导致死锁。
```text
INSERT INTO `order_record`(`order_no`, `status`, `create_date`)VALUES (5, 1, '2021-07-13 10:57:03');
```

锁的兼容矩阵图，来查看锁的兼容性：
![mysql锁相关](images/mysql锁相关.png)

### 避免死锁的措施
避免死锁最直观的方法就是在两个事务相互等待时，当一个事务的等待时间超过设置的某一阈值，就对这个事务进行回滚，另一个事务就可以继续执行了。这种方法简单有效，在InnoDB 中，参数 innodb_lock_wait_timeout 是用来设置超时时间的。

另外，我们还可以将 order_no 列设置为唯一索引列。虽然不能防止幻读，但我们可以利用它的唯一性来保证订单记录不重复创建，这种方式唯一的缺点就是当遇到重复创建订单时会抛出异常。

我们还可以使用其它的方式来代替数据库实现幂等性校验。例如，使用 Redis 以及ZooKeeper 来实现，运行效率比数据库更佳。

### 其它常见的 SQL 死锁问题
死锁的四个必要条件：互斥、占有且等待、不可强占用、循环等待。只要系统发生死锁，这些条件必然成立。所以在一些经常需要使用互斥共用一些资源，且有可能循环等待的业务场景中，要特别注意死锁问题。

InnoDB 存储引擎的主键索引为聚簇索引，其它索引为辅助索引。如果使用辅助索引来更新数据库，就需要使用聚簇索引来更新数据库字段。如果两个更新事务使用了不同的辅助索引，或一个使用了辅助索引，一个使用了聚簇索引，就都有可能导致锁资源的循环等待。由于本身两个事务是互斥，也就构成了以上死锁的四个必要条件了。

以上面的这个订单记录表来重现下聚簇索引和辅助索引更新时，循环等待锁资源导致的死锁问题：
![聚簇索引与辅助索引流程](images/聚簇索引与辅助索引流程.png)

UPDATE `order_record` SET `status` = 1 WHERE `order_no` = 4;

UPDATE `order_record` SET `status` = 1 WHERE `id` = 4;

出现死锁的步骤：
![聚簇索引与辅助索引](images/聚簇索引与辅助索引.png)

综上可知，在更新操作时，我们应该尽量使用主键来更新表字段，这样可以有效避免一些不必要的死锁发生。

解决死锁的最佳方式当然就是预防死锁的发生了，平时编程中，可以通过以下一些常规手段来预防死锁的发生：
```text
1. 在编程中尽量按照固定的顺序来处理数据库记录，假设有两个更新操作，分别更新两条相同的记录，但更新顺序不一样，有可能导致死锁；

2. 在允许幻读和不可重复读的情况下，尽量使用 RC 事务隔离级别，可以避免 gap lock 导致的死锁问题；

3. 更新表时，尽量使用主键更新；

4. 避免长事务，尽量将长事务拆解，可以降低与其它事务发生冲突的概率；

5. 设置锁等待超时参数，我们可以通过 innodb_lock_wait_timeout 设置合理的等待超时阈值，特别是在一些高并发的业务中，我们可以尽量将该值设置得小一些，避免大量事务等待，占用系统资源，造成严重的性能开销。
```

### MYSQL 处理死锁
MySQL默认开启了死锁检测机制，当检测到死锁后会选择一个最小(锁定资源最少得事务)的事务进行回滚

Innodb提供了wait-for graph算法来主动进行死锁检测，可以通过innodb_deadlock_detect = on 打开死锁检测。




