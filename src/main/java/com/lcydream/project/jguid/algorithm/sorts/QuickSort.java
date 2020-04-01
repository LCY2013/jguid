package com.lcydream.project.jguid.algorithm.sorts;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 交换元素
     * @param arr 数组
     * @param index 待交换下标
     * @param anotherIndex 待交换下标
     */
    private static void swap(int[] arr,int index,int anotherIndex){
        int temp = arr[index];
        arr[index] = arr[anotherIndex];
        arr[anotherIndex] = temp;
    }

    /**
     * 快排实现
     * @param arr 待排序数组
     * @param startIndex 快排开始下标
     * @param endIndex 快排结束下标
     * @return 排序好的数组
     */
    private static int[] quickSort(int[] arr,int startIndex,int endIndex){
        if(arr != null && (0 <= startIndex && startIndex < arr.length)
                && (0 <= endIndex && endIndex < arr.length)){
            //首先确定本次待排序的开始和结束下标
            int start = startIndex,end = endIndex;
            //确定本次循环的头节点
            int target = arr[startIndex];
            //开始循环从两头开始，到中间相遇结束
            for (;start < end;){
                //从右向左移动
                for (;start < end;){
                    if(arr[end] < target){
                        swap(arr,start,end);
                        break;
                    }else {
                        end--;
                    }
                }
                //从左向右移动
                for (;start < end;){
                    if(arr[start] > target){
                        swap(arr,start,end);
                        break;
                    }else {
                        start++;
                    }
                }
            }
            //确定target位置后，如果左边还有元素
            if((start - 1) > startIndex){
                quickSort(arr,startIndex,start - 1);
            }
            //确定target位置后，如果右边还有元素
            if((end + 1) < endIndex){
                quickSort(arr,end+1,endIndex);
            }
        }
        return arr;
    }

    private static int[] quickSortOther(int[] arr,int startIndex,int endIndex){
        if(arr != null && (0<=startIndex && startIndex < arr.length)
                && (0<=endIndex && endIndex < arr.length)){
            //设置开始，结束下标
            int start=startIndex,end=endIndex;
            //设置第一个目标节点
            int target = arr[startIndex];
            //开始从两端开始向中间移动
            while (start < end){
                //因为目标节点是从左开始的，所以先从右开始移动下标
                while (start < end){
                    if(arr[end] < target){
                        swap(arr,start,end);
                        Stream.of(QuickSort.toObjectArrays(arr)).forEach(QuickSort::print);
                        System.out.println("\n"+end);
                        break;
                    }else {
                        end--;
                    }
                }
                //从左开始先中间靠拢
                while (start < end){
                    if(arr[start] > target){
                        swap(arr,start,end);
                        Stream.of(QuickSort.toObjectArrays(arr)).forEach(QuickSort::print);
                        System.out.println("\n"+start);
                        break;
                    }else {
                        start++;
                    }
                }
            }
            Stream.of(QuickSort.toObjectArrays(arr)).forEach(QuickSort::print);
            System.out.println("\n-");
            //查看目标节点左边是否还存在节点，存在就继续快排
            if(start - 1 > startIndex){
                quickSortOther(arr,startIndex,start -1 );
            }
            //查看目标节点的右边是否存在节点，存在就继续快排
            if(end + 1 < endIndex){
                quickSortOther(arr,end + 1,endIndex);
            }
        }
        return arr;
    }

    /**
     * 获取一个待排序数组中一小段排序完成后就整体有序的下标位置
     * @param array 数组
     * @return
     */
    public int[] subSort(int[] array) {
        if(array == null || array.length == 0){
            return new int[]{-1,-1};
        }
        int start=-1,end=-1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0;i<array.length;i++){
            if(array[i] < max){
                end = i;
            }else {
                max = Math.max(array[i],max);
            }
            if(array[array.length - 1 - i] > min){
                start = array.length - 1 - i;
            }else {
                min = Math.min(array[array.length - 1 - i],min);
            }
        }

        return new int[]{start,end};
    }

    public static void main(String[] args) {
        int[] arr = {2,1,4,6,3,7,8,9,0,5};
        /*
            0,1,4,6,3,7,8,9,2,5
            0,1,2,6,3,7,8,9,4,5
            start = 2 end = 8

            0,1,2,6,3,7,8,9,4,5
            start = 2 end = 8



         */
        //quickSort(arr,1,arr.length-1);
        quickSortOther(arr,0,arr.length-1);
        //Stream.of(QuickSort.toObjectArrays(arr)).forEach(QuickSort::print);
    }

    private static void print(Integer num){
        System.out.printf("%d \t",num);
    }

    private static Integer[] toObjectArrays(int[] intArrays){
        Integer[] integers = new Integer[intArrays.length];
        for (int i=0 ; i < intArrays.length ; i++){
            integers[i] = intArrays[i];
        }
        return integers;
    }
}
