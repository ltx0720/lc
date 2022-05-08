package week04;

public class 寻找峰值 {

    /**
     * 三分
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int lmid = (l + r) / 2;
            int rmid = lmid + 1;
            if (nums[lmid] < nums[rmid]) {
                l = lmid + 1;
            } else {
                r = rmid - 1;
            }
        }
        return l;
    }
}
