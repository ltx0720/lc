package week01;

public class T66 {
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return digits;
        }

        int num = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = (digits[i] + num);
            int res = sum % 10;
            num = sum / 10;
            digits[i] = res;
            if (num != 1) {
                break;
            }
        }

        if (num == 0) {
            return digits;
        }

        int[] d = new int[digits.length + 1];
        d[0] = num;
        for (int i = 1; i < d.length; i++) {
            d[i] = digits[i - 1];
        }
        return d;
    }
}
