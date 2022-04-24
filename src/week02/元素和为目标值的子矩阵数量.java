package week02;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 元素和为目标值的子矩阵数量
 */
public class 元素和为目标值的子矩阵数量 {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int count = 0;
        int[][] sum = new int[matrix.length][matrix[0].length];
        sum[0][0] = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int row = Math.max(i - 1, 0);
                int column = Math.max(j - 1, 0);
                sum[i][j] = sum[row][j] + sum[i][column] - sum[row][column] + matrix[i][j];
                if (sum[i][j] == target) {
                    count++;
                }
            }
        }
        return count;
    }


    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static int[][] stringToInt2dArray(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        if (jsonArray.size() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
            JsonArray cols = jsonArray.get(i).asArray();
            arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] matrix = stringToInt2dArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int ret = new 元素和为目标值的子矩阵数量().numSubmatrixSumTarget(matrix, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }


}
