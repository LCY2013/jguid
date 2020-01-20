package com.lcydream.project.jguid.datastructure.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        LinkedHashMap<String,String> accessOrderedMap = new LinkedHashMap<>(){
            //定义删除规则,根据key的hash码顺序删除数据
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3;
            }
        };

        //添加数据
        accessOrderedMap.put("Project1","2");
        accessOrderedMap.put("Project2","1");
        accessOrderedMap.put("Project3","3");
        accessOrderedMap.forEach((k,v)-> System.out.println(k+":"+v));

        //添加新元素，触发删除规则
        accessOrderedMap.put("Project4","4");
        accessOrderedMap.put("Project1","5");
        accessOrderedMap.put("Project1","1");
        System.out.println("oldest entry should be removed");
        accessOrderedMap.forEach((k,v)-> System.out.println(k+":"+v));

    }

}
