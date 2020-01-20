package com.lcydream.project.jguid.datastructure.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueTest {

    public static void main(String[] args) {
        //初始化一个java标准库实现的优先队列
        //PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(8, Comparator.comparingInt(val -> val));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(8, (val1 , val2) -> val2-val1);

        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(7);
        priorityQueue.add(3);
        priorityQueue.add(9);
        priorityQueue.add(19);
        priorityQueue.add(34);
        priorityQueue.add(3);
        priorityQueue.add(6);

        /*for (Integer integer : priorityQueue){
            System.out.println(integer);
        }*/
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

    }

}