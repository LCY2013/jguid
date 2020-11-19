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

### 相关压测工具
```
yum install -y httpd-tools

ab -n 10000 -c 1000 http://ip:port/uri
-n 请求数 /-c 并发用户数来模拟线上的峰值请求
```

### 字符串中的正则表达式
```
正则表达式使用一些特定的元字符来检索、匹配以及替换符合规则的字符串。

构造正则表达式语法的元字符，由普通字符、标准字符、限定字符（量词）、定位字符（边界字符）组成。

1. 贪婪模式（Greedy）
    顾名思义，就是在数量匹配中，如果单独使用 +、 ? 、* 或{min,max} 等量词，正则表达式会匹配尽可能多的内容。
例如，上边那个例子：
    text=“abbc”
    regex=“ab{1,3}c”
就是在贪婪模式下，NFA 自动机读取了最大的匹配范围，即匹配 3 个 b 字符。匹配发生了一次失败，就引起了一次回溯。
如果匹配结果是“abbbc”，就会匹配成功。
text="abbbc"
regex="ab{1,3}c"

2. 懒惰模式（Reluctant）
    在该模式下，正则表达式会尽可能少地重复匹配字符。如果匹配成功，它会继续匹配剩余的字符串。
例如，在上面例子的字符后面加一个“？”，就可以开启懒惰模式。
    text=“abc”
    regex=“ab{1,3}?c”
匹配结果是“abc”，该模式下 NFA 自动机首先选择最小的匹配范围，即匹配 1 个 b 字符，因此就避免了回溯问题。

3. 独占模式（Possessive）
    同贪婪模式一样，独占模式一样会最大限度地匹配更多内容；不同的是，在独占模式下，匹配失败就会结束匹配，不会发生回溯问题。
还是上边的例子，在字符后面加一个“+”，就可以开启独占模式。
    text=“abbc”
    regex=“ab{1,3}+bc”

避免回溯问题，使用懒惰模式和独占模式。

比如提取域名以及后面参数信息：
\\?(([A-Za-z0-9-~_=%]+\\&{0,1})+)
\\?(([A-Za-z0-9-~_=%]++\\&{0,1})+)  优化
```

### 正则表达式优化策略
```
1. 少用贪婪模式，多用独占模式贪婪模式会引起回溯问题，我们可以使用独占模式来避免回溯。

2. 减少分支选择分支选择类型“(X|Y|Z)”的正则表达式会降低性能，在开发的时候要尽量减少使用。
    如果一定要用，我们可以通过以下几种方式来优化：
  首先，我们需要考虑选择的顺序，将比较常用的选择项放在前面，使它们可以较快地被匹配；
  其次，我们可以尝试提取共用模式，例如，将“(abcd|abef)”替换为“ab(cd|ef)”，后者匹配速度较快，因为 NFA 自动机会尝试匹配 ab，如果没有找到，就不会再尝试任何选项；
  最后，如果是简单的分支选择类型，我们可以用三次 index 代替“(X|Y|Z)”，如果测试的话，你就会发现三次 index 的效率要比“(X|Y|Z)”高出一些

3. 减少捕获嵌套
    什么是捕获组和非捕获组?
      捕获组是指把正则表达式中，子表达式匹配的内容保存到以数字编号或显式命名的数组中，方便后面引用，一般一个 () 就是一个捕获组，捕获组可以进行嵌套。
      非捕获组则是指参与匹配却不进行分组编号的捕获组，其表达式一般由（?:exp）组成。
    在正则表达式中，每个捕获组都有一个编号，编号 0 代表整个匹配到的内容。
        
减少不需要获取的分组，可以提高正则表达式的性能。
```












