### 阻塞队列

ArrayBlockingQueue：一个基于数组结构实现的有界阻塞队列，按 FIFO（先进先出）原则对元素进行排序，使用 ReentrantLock、Condition 来实现线程安全

LinkedBlockingQueue：一个基于链表结构实现的阻塞队列，同样按 FIFO （先进先出） 原则对元素进行排序，使用 ReentrantLock、Condition 来实现线程安全，吞吐量通常要高于 ArrayBlockingQueue

PriorityBlockingQueue：一个具有优先级的无限阻塞队列，基于二叉堆结构实现的无界限（最大值Integer.MAX_VALUE - 8）阻塞队列，队列没有实现排序，但每当有数据变更时，都会将最小或最大的数据放在堆最上面的节点上，该队列也是使用了 ReentrantLock、Condition 实现的线程安全

DelayQueue：一个支持延时获取元素的无界阻塞队列，基于 PriorityBlockingQueue 扩展实现，与其不同的是实现了 Delay 延时接口

SynchronousQueue：一个不存储多个元素的阻塞队列，每次进行放入数据时, 必须等待相应的消费者取走数据后，才可以再次放入数据，该队列使用了两种模式来管理元素，一种是使用先进先出的队列，一种是使用后进先出的栈，使用哪种模式可以通过构造函数来指定

### 非阻塞队列

ConcurrentLinkedQueue：常用的线程安全的非阻塞队列是ConcurrentLinkedQueue，它是一种无界线程安全队列(FIFO)，基于链表结构实现，利用 CAS 乐观锁来保证线程安全。

