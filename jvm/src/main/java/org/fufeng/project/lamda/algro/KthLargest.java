package org.fufeng.project.lamda.algro;

import java.util.PriorityQueue;

/**
 * @ClassName: HeapDemo
 * @author: LuoChunYun
 * @Date: 2019/5/5 17:16
 * @Description: TODO
 */
public class KthLargest {

    private final PriorityQueue<Integer> minHeap;
    private final int number;

    /**
     * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
     *
     * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，
     * 它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
     *
     * 示例:
     *
     * int k = 3;
     * int[] arr = [4,5,8,2];
     * KthLargest kthLargest = new KthLargest(3, arr);
     * kthLargest.add(3);   // returns 4
     * kthLargest.add(5);   // returns 5
     * kthLargest.add(10);  // returns 5
     * kthLargest.add(9);   // returns 8
     * kthLargest.add(4);   // returns 8
     * 说明:
     * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
     */
    public KthLargest(int k, int[] nums) {
        this.number = k;
        this.minHeap = new PriorityQueue<>(k);
        for (int num : nums){
            add(num);
        }
    }

    public int add(int val) {
        if(minHeap.size() < number){
            minHeap.offer(val);
        }else if(minHeap.peek() < val){
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println(kthLargest.add(3));   // returns 4
        System.out.println(kthLargest.add(5));   // returns 5
        System.out.println(kthLargest.add(10));  // returns 5
        System.out.println(kthLargest.add(9));   // returns 8
        System.out.println(kthLargest.add(4));   // returns 8
    }
}
