//package com.lcydream.project.jguid.datastructure.map;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class MapTest {
//
//    public static void main(String[] args) {
//        LinkedHashMap<String,String> accessOrderedMap = new LinkedHashMap<>(){
//            //定义删除规则,根据key的hash码顺序删除数据
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
//                return size() > 3;
//            }
//        };
//
//        //添加数据
//        accessOrderedMap.put("Project1","2");
//        accessOrderedMap.put("Project2","1");
//        accessOrderedMap.put("Project3","3");
//        accessOrderedMap.forEach((k,v)-> System.out.println(k+":"+v));
//
//        //添加新元素，触发删除规则
//        accessOrderedMap.put("Project4","4");
//        accessOrderedMap.put("Project1","5");
//        accessOrderedMap.put("Project1","1");
//        System.out.println("oldest entry should be removed");
//        accessOrderedMap.forEach((k,v)-> System.out.println(k+":"+v));
//
//        System.out.println(tableSizeFor(3));
//        System.out.println(tableSize(6));
//    }
//
//    static final int MAXIMUM_CAPACITY = 1073741824;
//
//    /**
//     * jdk 11 for HashMap
//     * @param cap 容量
//     * @return
//     */
//    static final int tableSizeFor(int cap) {
//        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
//        return n < 0 ? 1 : (n >= 1073741824 ? 1073741824 : n + 1);
//    }
//
//    /**
//     * jdk 8 for HashMap
//     * @param cap 容量
//     * @return
//     */
//    static final int tableSize(int cap) {
//        int n = cap - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }
//
//}
