### ArrayList 
ArrayList 实现了 List 接口，继承了 AbstractList 抽象类，底层是数组实现的，并且实现了自增扩容数组大小。

ArrayList 还实现了 Cloneable 接口和 Serializable 接口，所以他可以实现克隆和序列化。

ArrayList 还实现了 RandomAccess 接口，这个接口其实是一个空接口，什么也没有实现，RandomAccess 接口是一个标志接口，标志着“只要实现该接口的 List 类，都能实现快速随机访问，在查询的时候会判断是否有该接口，如果有就直接进行二分查找。

ArrayList中的参数信息如下：
```
// 默认初始化容量    
private static final int DEFAULT_CAPACITY = 10;   
// 对象数组    
transient Object[] elementData;     
// 数组长度    
private int size;
```

#### 问题：为啥elementData用transient修饰呢？
```
ArrayList 实现了Serialized接口，可以用于序列化，但是这里存储的数据又是transient修饰，不是自相矛盾？

这里引入JAVA关于序列化的两个方法java.util.ArrayList.writeObject()和java.util.ArrayList.readObject()
这两个方法是序列化和反序列化的两个主要方法，通过writeObject()对elementData进行了序列化，节约了内存，只对存放了数据的数组位置进行存储，也让其他外部方法序列化ArrayList的时候不用序列化整个elementData数组。
```

### LinkedList
inkedList 是基于双向链表数据结构实现的，LinkedList 定义了一个 Node 结构，Node结构中包含了 3 个部分：元素内容 item、前指针 prev 以及后指针 next。
```
// 元素
E item;
// 后置指针
Node<E> next;
// 前置指针
Node<E> prev;

Node(Node<E> prev, E element, Node<E> next) {
    this.item = element;
    this.next = next;
    this.prev = prev;
}
```

LinkedList 就是由 Node 结构对象连接而成的一个双向链表，在 JDK1.7 之前，LinkedList 中只包含了一个 Entry 结构的 header 属性，并在初始化的时候默认创建一个空的 Entry，用来做 header，前后指针指向自己，形成一个循环双向链表。

jdk1.7后的好处：

first/last 属性能更清晰地表达链表的链头和链尾概念

first/last 方式可以在初始化 LinkedList 的时候节省 new 一个 Entry

first/last 方式最重要的性能优化是链头和链尾的插入删除操作更加快捷了

LinkedList 没有实现了 RandomAccess 接口，因为链表的特点是不连续的，没办法通过索引进行直接访问。

LinkedList 的两个重要属性 first/last 属性，其实还有一个 size 属性，这三个属性都被 transient 修饰了，原因很简单，我们在序列化的时候不会只对头尾进行序列化，所以 LinkedList 也是自行实现 readObject 和 writeObject 进行序列化与反序列化。

### 问题：ArrayList与LinkedList相比特点?
```
ArrayList 底层存储结构是数组  
LinkedList 底层存储结构是链表

数组相较于链表在整体上查询快，增删慢，因为涉及数据的数据移位重组，而链表则是查询相对要慢一点，因为它需要通过节点的遍历，而数组通过下表拿到索引的位置，直接访问。

但是在ArrayList中，新增如果是利用的add(E e)，这个方法，那它就只会在数组末尾添加数据，如果在使用ArrayList前就已经知道了大约的存储数据量，
这个时候构造一个确定容量的数组大小，然后通过add(E e)追加数组末尾的方式,ArrayList在这种情况下的新增操作在避免动态扩容的情况下是要比大多数List效率高，
但是大多数时候的删除就要对数组中数据进行移位重组，它的效率相较其他List就比较低了，而HikariCP 中数据库连接池重写了一个叫FastList的List数据结构，
主要通过确定List容量和一个叫removeLast()去获取连接来达到不移动数组中的数据元素，提高效率。
```





















