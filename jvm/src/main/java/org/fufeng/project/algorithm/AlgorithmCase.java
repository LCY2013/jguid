package org.fufeng.project.algorithm;

/**
 * Algorithm
 * 算法实验题
 * @author Luo Chun Yun
 * @email 1475653689@qq.com
 * @date 2019/3/8 14:02
 */
public class AlgorithmCase {

	public static void main(String[] args) {
		AlgorithmCase algorithmCase = new AlgorithmCase();
		algorithmCase.outNumber(new int[]{2,2,3,4,5,4,5,7,8,9,9,8,7});
		System.out.println(1 & 2);
		System.out.println(1 | 2);
		System.out.println(1 ^ 2);

		System.out.println("---------");
		//按位右移16位，不足补零
		System.out.println(16 >>> 16);
		//按位右移16位
		System.out.println(16 >> 16);
		System.out.println(16 > 16);

		//hash
		int h;
		Object key="1";
		//key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	/**
	 * 找出一个非空数组中，唯一存在的单一元素
	 * @param nums 数组
	 */
	void outNumber(int[] nums){
		int YIHUO = 0;
		for(int num : nums){
			YIHUO = YIHUO ^ num;
			System.out.println("->"+YIHUO);
		}
		System.out.println("result:"+YIHUO);
	}
}
