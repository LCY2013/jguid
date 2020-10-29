package com.lcydream.project.jguid.algorithm.stack;

import java.util.stream.Stream;

/**
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 * <p>
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 * <p>
 *  
 * <p>
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
 * <p>
 * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
 * A 或 B 中的元素在原字符串中可以不连续。
 * A.length + B.length = seq.length
 * max(depth(A), depth(B)) 的可能取值最小。
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
 * <p>
 * answer[i] = 0，seq[i] 分给 A 。
 * answer[i] = 1，seq[i] 分给 B 。
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class maxDepthAfterSplit {

    public static int[] maxDepthAfterSplit(String seq) {
        if (seq == null || seq.length() == 0) {
            return new int[]{0};
        }
        //记录游标位置
        int deep = 0;
        //记录分组后的情况
        int[] result = new int[seq.length()];
        for (int i = 0; i < result.length; i++) {
            final char c = seq.charAt(i);
            switch (seq.charAt(i)) {
                case '(':
                    deep += 1;
                    result[i] = deep % 2;
                    break;
                default:
                    result[i] = deep % 2;
                    deep -= 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] depthAfterSplit = maxDepthAfterSplit("(()())");
        //1 2 2 2 2 1
        for (int i = 0; i < depthAfterSplit.length; i++) {
            System.out.printf("%d \t", depthAfterSplit[i]);
        }
    }
}
