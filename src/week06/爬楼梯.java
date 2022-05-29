package week06;

public class 爬楼梯 {
    public int climbStairs(int n) {
        int m = 2;
        int[] num = new int[m+1];
        num[0] = 0;
        num[1] = 1;
        num[2] = 2;
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i=1; i<num.length; i++) {
            for (int j=n; j>=num[i]; j--) {
                if (j >= num[i]) {
                    dp[j] += dp[j-num[i]];
                }
            }
        }

        return dp[n];
    }
}
