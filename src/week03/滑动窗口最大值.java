package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 滑动窗口最大值 {

    private Node[] heap;

    private int tail = 1;

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        int[] res = new int[nums.length + 1 - k];
        heap = new Node[nums.length + 1];
        int resIndex = 0;
        int dataIndex;

        for (dataIndex = 0; dataIndex < k - 1; dataIndex++) {
            insert(nums[dataIndex], dataIndex);
        }

        while (dataIndex < nums.length) {
            insert(nums[dataIndex], dataIndex);
            while (dataIndex - k >= heap[1].index && tail > 1) {
                popHead();
            }
            dataIndex++;
            res[resIndex++] = heap[1].val;
        }

        return res;
    }


    private void insert(int num, int index) {
        heap[tail++] = new Node(index, num);
        heapifyUp();
    }

    private void heapifyUp() {
        int index = tail - 1;
        while (index > 1) {
            int father = index / 2;
            if (heap[father].val < heap[index].val) {
                Node temp = heap[index];
                heap[index] = heap[father];
                heap[father] = temp;
            }
            index = father;
        }
    }

    private void heapifyDown() {
        int index = 1;
        while (index < tail - 1) {
            int child = index * 2;
            int otherChild = child + 1;
            if (otherChild <= tail - 1 && heap[otherChild].val > heap[child].val) {
                child = otherChild;
            }

            if (child <= tail - 1 && heap[child].val > heap[index].val) {
                Node temp = heap[index];
                heap[index] = heap[child];
                heap[child] = temp;
            }
            index = child;
        }

    }

    private int popHead() {
        Node head = heap[1];
        heap[1] = heap[tail - 1];
        heap[tail - 1] = head;
        tail--;
        heapifyDown();
        return head.val;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int[] ret = new 滑动窗口最大值().maxSlidingWindow(nums, k);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}

class Node {
    int index;
    int val;

    public Node(int index, int val) {
        this.index = index;
        this.val = val;
    }
}
