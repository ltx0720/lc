package week07;

public class 跳跃游戏II {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i=0; i<dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        for (int i=0; i<nums.length; i++) {
            int k = nums[i];
            while (k > 0 ) {
                if (i+k < nums.length) {
                    dp[i+k] = Math.min(dp[i+k], dp[i] + 1);
                }
                k--;
            }
        }

        return dp[nums.length - 1];
    }
}
