package week02;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 */
public class 数组的度 {

    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> startIndexMap = new HashMap<>();

        int max = 0;
        // 当前度最大的子序列的最后一个index
        int lastIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            Integer count = countMap.get(nums[i]);
            if (count == null) {
                startIndexMap.put(nums[i], i);
                count = 0;
            }
            countMap.put(nums[i], count + 1);

            // max相等但子序列更长直接舍弃
            if ((count + 1) == max && i - startIndexMap.get(nums[i]) > lastIndex - startIndexMap.get(nums[lastIndex])) {
                continue;
            }

            // max相等但长度小于等于
            if ((count + 1) >= max) {
                max = count + 1;
                lastIndex = i;
            }
        }
        Integer startIndex = startIndexMap.get(nums[lastIndex]);
        return lastIndex - startIndex + 1;
    }

    public static void main(String[] args) {
        数组的度 数组的度 = new 数组的度();
        int[] nums = new int[]{1, 2, 1};
        int length = 数组的度.findShortestSubArray(nums);
        System.out.println(length);
    }


}
