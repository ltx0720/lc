package week06;

public class 最长递增子序列的个数 {
    public int findNumberOfLIS(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        if (nums.length == 2) {
            if (nums[1] <= nums[0]) {
                return 2;
            }
        }
        int len = 0, pre = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                // len ++;
                dp[i] = dp[i-1];
                pre = dp[i];
            }
            else if (nums[i] == nums[i-1] || nums[i] > pre) {
                dp[i] = dp[i-1] + 1;
            } else {
                dp[i] = dp[i-1];
            }
            System.out.println(dp[i]);
        }

        return dp[nums.length - 1];
    }
}
