package org.fufeng.project.concurrent.pretty.cacheline;

import sun.misc.Contended;

/**
 * @ClassName: CacheLineContent
 * @author: LuoChunYun
 * @Date: 2019/5/19 19:24
 * @Description: TODO
 *  测试CPU一级缓存中数据在缓存行中的存储情况？
 *
 *  这里也就是所谓的缓存伪共享，因为一个缓存行同时只能有一个线程去访问，
 *  所以多个线程访问同一个具有多个变量的缓存行的时候会造成一定的性能下降。
 *  同理也可以得到单线程访问一个具有多个变量的缓存行的时候还会启到加速的作用。
 *
 *   JDK1.8以前，解决伪共享的方式是数据填充，让每一个变量占一个缓存行
 *   如果一个缓存行是64位，而一个lang类型是8位，所以我们要自己在类中
 *   添加p1到p6去填充48位，还要value的8位与这个对象的对象头占的8位，满足一个缓存行
 *
 *   public class FiledLong{
 *       public volatile long value = 0L'
 *       public long p1,p2,p3,p4,p5,p6;
 *   }
 *
 *   JDK1.8后,利用的是{@link Contended},不过这个注解主要是作用与java的核心类上面的，
 *   也就是rt包下的类，如果要作用于用户类路径下的类，需要设置JVM参数：-XX:-RestrictContended,
 *   默认的填充宽度是128位，可以自定义宽度JVM参数：-XX:-ContendedPaddingWidth参数
 */
public class CacheLineContent {

    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        contentOne();
        contentTwo();
        /**
         * 比较结果：
         *  第一组：
         *   cacheTime contentOne:4237600
         *   cacheTime contentTwo:10816700
         *  第二组：
         *    cacheTime contentOne:7066400
         *    cacheTime contentTwo:14983200
         *
         *  比较两组数据可以看出，在缓存行内存中地址连续的操作比不连续的操作要提高一半以上的效率
         *
         *  原因：
         *    CPU的一级缓存中的基本存储单位是缓存行，而连续的变量是会被一次加入到缓存行中，
         *    因为当访问连续的变量时候，会把后面的一部分元素一块放入缓存行中，这样就会
         *    提高一级缓存的命中率，就不会去主存中读取了，而非连续性的访问数组中的元素，
         *    这就会破坏程序访问的局部性原则，并且缓存容量是有限制的，当缓存满了的时候就会
         *    根据一定的淘汰算法规则替换缓存行，这就可能会导致从主内存加载过来的缓存行元素还没有
         *    被使用到就被替换掉，造成了程序运行性能的下降。
         *
         */
    }

    /**
     * 测试数组不连续的读取，看看是否会变慢，与contentOne对比
     */
    private static void contentTwo() {
        //申请一个1024*1024的数组
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        //记录开始时间
        long startTime = System.nanoTime();
        for (int i=0;i<COLUM_NUM;i++){
            for (int j=0;j<LINE_NUM;j++){
                array[j][i] = j*2+i;
            }
        }
        //记录结束时间
        long endTime = System.nanoTime();
        //记录读取缓存连续的缓存行使用的时间
        long cacheTime = endTime - startTime;

        System.out.println("cacheTime contentTwo:"+cacheTime);
    }

    /**
     * 测试数组连续的读取，看缓存行是否会读取连续的多个变量
     */
    private static void contentOne() {
        //申请一个1024*1024的数组
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        //记录开始时间
        long startTime = System.nanoTime();
        for (int i=0;i<LINE_NUM;i++){
            for (int j=0;j<COLUM_NUM;j++){
                array[i][j] = i*2+j;
            }
        }
        //记录结束时间
        long endTime = System.nanoTime();
        //记录读取缓存连续的缓存行使用的时间
        long cacheTime = endTime - startTime;

        System.out.println("cacheTime contentOne:"+cacheTime);
    }

}
