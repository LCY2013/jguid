/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-20
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.collection.basic;

import java.util.*;

/**
 * @program: jguid
 * @description: {@link Map} 和 {@link Set} 它们有什么联系呢？
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-20
 */
public class MapAndSetInfo {

    public static void main(String[] args) {
        // HashSet 的实现其实是由HashMap的key位实现而来
        // 直接利用了HashMap key的散列码
        Map<String, Object> map = new HashMap<String, Object>();
        Set<String> set = new HashSet<String>();
        // tab[1] hashCode = 9 key = 'a'
        // tab[2] hashCode = 10
        // tab[3] hashCode = 9 key = 'a'
        // TreeSet 底层运用了 TreeMap
        // 二叉树索引
        map = new TreeMap<>();
        set = new TreeSet<>();

        // Integer,String implements Comparable
        // 3 1 2 2
        // 3 1 => 1 3
        // (1 3) 2 => 1 2 3
        // (1 2 3) 2 => 1 2 2 3

        // 一致性 Hash ：https://en.wikipedia.org/wiki/Consistent_hashing

        // 负载均衡算法：Spring Cloud 负载均衡不成熟的点 - 缺少一致性 Hash 算法
        // 服务节点：A B C 可以均衡服务
        // 3000 请求，平均 1000 个请求
        // 尽可能平均、支持动态扩缩容 D E -> 平均 600 请求

        // TreeMap 实现 一致性 Hash
        // https://github.com/Jaskey/ConsistentHash/blob/master/src/com/github/jaskey/consistenthash/ConsistentHashRouter.java
        // 服务节点：A B C 可以均衡服务
        // 正常情况 A B C -> A
        // 缩容或异常 A 情况 B C -> B
        // C -> C

        // 更公平的实现 ：RendezvousHash
        // 原理：https://en.wikipedia.org/wiki/Rendezvous_hashing
        // 实现：https://github.com/clohfink/RendezvousHash


        // LinkedHashMap
        // 顺序：插入顺序（默认）、访问顺序（构造器调整）
        // 访问顺序：LRU
    }

}
