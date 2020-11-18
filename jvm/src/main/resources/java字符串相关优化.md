### java 中 String 的变化历史
1. 在 Java6 以及之前的版本中
    
    String 对象是对 char 数组进行了封装实现的对象，主要有四个成员变量：char 数组、偏移量 offset、字符数量 count、哈希值 hash。
    
    String 对象是通过 offset 和 count 两个属性来定位 char[] 数组，获取字符串。这么做可以高效、快速地共享数组对象，同时节省内存空间，但这种方式很有可能会导致内存泄漏。

2. 从 Java7 版本开始到 Java8 版本
    
    Java 对 String 类做了一些改变。String 类中不再有offset 和 count 两个变量了。这样的好处是 String 对象占用的内存稍微少了些，同时，String.substring 方法也不再共享 char[]，从而解决了使用该方法可能导致的内存泄漏问题。
    
3. 从 Java9 版本开始
    
    将 char[] 字段改为了 byte[] 字段，又维护了一个新的属性coder，它是一个编码格式的标识。

    为了防止浪费，将char(两个字节)改成了byte(一个字节)，通过coder这个字段判断是否是单字节(单字节 LATIN1 = 0，多字节 UTF16  = 1)。

### Java 中 String 字符串创建方式以及创建的具体过程
```
创建方式:
    1、String str = "a";
    2、String str = new String("a");
两种创建方式的具体过程:
    1、"a" 会先去字符串常量池中查询是否存在，如果存在就直接引用，如果不存在就在常量池中创建该字符串对象，然后引用给str。
    2、new String("a") 首先是"a"这个字符串先进入常量池查询，如果不存在就在常量池中进行创建，如果已经存在了就将该常量池中的引用给new 的String对象，返回将new的String引用给str。
```

### Java 中 String 对象优化
#### 如何构建超大字符串
```
String str= "ab" + "cd" + "ef" 这个过程会产生多少个String对象？
"ab" 、 "cd" 、 "ef" 、 "abcd" 、 "abcdef"
实际上？
    jvm 编译后 String str = "abcdef";

for(int i=0; i<1000; i++) {     
   str = str + i;
}
实际上？
for(int i=0; i<1000; i++) {                  
    str = (new StringBuilder(String.valueOf(str))).append(i).toString();
}

如果存在字符串拼接，尽量使用StringBuilder。
```

#### 利用String.intern()提升性能
```
比如 发布一条博客时需要同步位置信息，位置信息设置如下:
public class Location{
    private String city;
    private String region;
    private String countryCode;
    private double longithde;
    private double latithde;
}

优化第一步：
考虑到城市相关信息大部分人都相同，抽出这类型的共享数据
public class ShareLocation{
    private String city;
    private String region;
    private String countryCode;
}
public class Location{
    private ShareLocation shareLocation;
    private double longithde;
    private double latithde;
}

优化第二步：
如果存在大量String类型数据，利用String.intern()，压缩字符串对象内存占用率
ShareLocation shareLocation = new ShareLocation();
shareLocation.setCity(messageInfo.getCity().intern());
shareLocation.setRegion(messageInfo.getRegion().intern());
shareLocation.setCountryCode(messageInfo.getCountryCode().intern());

Location location = new Location();
location.setShareLocation(shareLocation);
location.setLongithde(messageInfo.getLongithde());
location.setLatithde(messageInfo.getLatithde());
```
使用 intern 方法需要注意的一点是，一定要结合实际场景，因为常量池的实现是类似于一个 HashTable 的实现方式，HashTable 存储的数据越大，遍历的时间复杂度就会增加，如果数据过大，会增加整个字符串常量池的负担。

尽量不使用String.split(reg)，因为正则表达式的回溯问题，可以使用String.indexOf()进行查询分割。

下面的情况可以使用String.split()操作,因为在jdk1.8开始下面的情况不会使用正则表达式：
```
第一种为传入的参数长度为1，且不包含“.$|()[{^?*+\\”regex元字符的情况下，不会使用正则表达式
第二种为传入的参数长度为2，第一个字符是反斜杠，并且第二个字符不是ASCII数字或ASCII字母的情况下，不会使用正则表达式
```
















