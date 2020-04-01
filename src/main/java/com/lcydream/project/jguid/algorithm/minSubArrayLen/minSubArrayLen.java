package com.lcydream.project.jguid.algorithm.minSubArrayLen;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class minSubArrayLen {

    public static int minSubArrayLen(int s, int[] nums) {
        if(nums==null || nums.length == 0 || (nums.length == 1 && nums[0] < s)){
            return 0;
        }
        if(nums[0] >= s){
            return 1;
        }
        //定义两个游标，向滑动窗口一样计算最小的子数组
        int startIndex = 0,endIndex = startIndex+1;
        //定义最小范围
        int min = Integer.MAX_VALUE;
        for(;;){
            if(startIndex == endIndex){
                break;
            }
            if(nums[endIndex] >= s){
                return 1;
            }
            int sum = 0;
            for(int i = startIndex;i<=endIndex;i++){
                sum += nums[i];
            }
            if(sum < s && endIndex == nums.length - 1){
                break;
            }
            if(sum >= s){
                min = Math.min(min,(endIndex-startIndex+1));
                startIndex++;
            }else {
                if(endIndex < nums.length-1) {
                    endIndex++;
                }
            }
        }
        return min==Integer.MAX_VALUE?0:min;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(11,new int[]{1,2,3,4,5}));
    }
}
