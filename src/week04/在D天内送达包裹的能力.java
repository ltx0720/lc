package week04;

import java.util.HashMap;

public class 在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int days) {
        int max = 0, sum = 0;
        for (int i=0; i<weights.length; i++) {
            max = Math.max(max, weights[i]);
            sum += weights[i];
        }

        int left = max, right = sum;
        while (left < right) {
            int mid = (left + right) / 2;
            int count = getCount(weights, mid);
            if (count <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println();
        return left;
    }

    private int getCount(int[] weights, int weight) {
        int count = 1;
        int cw = weight;
        for (int i=0; i<weights.length; i++) {
            if (cw < weights[i]) {
                cw = weight;
                count++;
            }
            cw -= weights[i];
        }
        return count;
    }
}
