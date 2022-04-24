package week02;

import java.util.HashMap;
import java.util.Map;

public class 和为K的子数组 {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int ans = 0, sumi = 0;
        for (int i=0; i<nums.length; i++) {
            sumi += nums[i];
            int sumj = sumi - k;

            if (map.containsKey(sumj)) {
                ans += map.get(sumi);
            }

            map.put(sumi, map.getOrDefault(sumi, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {

        和为K的子数组 和为K的子数组 = new 和为K的子数组();
        int[] nums = new int[] {5,2,-2,4,0};
        和为K的子数组.subarraySum(nums, 2);
    }

}
