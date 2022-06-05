package week07;

public class 跳跃游戏 {
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i=0; i<nums.length; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j=i+1; j<=i+nums[i]; j++) {
                if (j > nums.length - 1) {
                    break;
                }
                dp[j] = true;
            }
        }

        return dp[nums.length - 1];
    }
}
