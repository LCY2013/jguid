/*
 * The MIT License (MIT)
 * ------------------------------------------------------------------
 * Copyright © 2019 Ramostear.All Rights Reserved.
 *
 * ProjectName: jguid
 * @Author : <a href="https://github.com/lcy2013">MagicLuo</a>
 * @date : 2020-07-27
 * @version : 1.0.0-RELEASE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.fufeng.os.concurrency.container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @program: jguid
 * @description: {@link Map.Entry} Map 实体信息
 * @author: <a href="https://github.com/lcy2013">MagicLuo</a>
 * @create: 2020-07-27
 * @see java.util.Map.Entry
 */
public class MapEntryInfo {

    public static void main(String[] args) {
        // 创建一个Map实例
        Map<String,Integer> map = new HashMap<>();
        map.put("A",1);
        map.put("B",2);
        map.put("C",3);

        // map操作示例
        infoMap(map);

        map.forEach((key,value) -> System.out.println(key + "->" + value));

        // Concurrent Map Map.Entry#setValue(Object) 没有具体实现
        Map<String,Integer> concurrentMap = new ConcurrentSkipListMap<>();
        concurrentMap.put("A",1);
        concurrentMap.put("B",2);
        concurrentMap.put("C",3);

        // java.lang.UnsupportedOperationException
        // 因为concurrent map 没有实现Map.Entry#setValue(Object)
        infoMap(concurrentMap);

        map.forEach((k,v) -> System.out.println(k + "->" + v));
    }

    /**
     *  map示例演示
     * @param map map实例
     */
    private static void infoMap(Map<String, Integer> map) {
        // 给所有map的value统一加上一
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            // 一步一步操作
            slowApproach(entry,map);

            // 一步操作
            fastApproach(entry);
        }
    }

    private static void fastApproach(Map.Entry<String, Integer> entry) {
        entry.setValue(entry.getValue()+1);
    }

    private static void slowApproach(Map.Entry<String, Integer> entry,Map<String,Integer> map) {
        // 先获取key
        final String key = entry.getKey();
        // 再获取value
        Integer value = entry.getValue();
        // 计算value值加一
        value++;
        // 最后设值
        map.put(key,value);
    }

}
