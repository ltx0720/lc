package week03;

import java.util.*;

public class 课程表II {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            int[] res = new int[numCourses];
            for (int i=0; i<res.length; i++) {
                res[i] = i;
            }
            return res;
        }

        // 存放结果
        int[] res = new int[numCourses];
        int index = 0;
        // 出边数组
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 度
        int[] in = new int[numCourses];
        // 栈
        LinkedList<Integer> stack = new LinkedList<>();
        // 可以学完的课程数量
        int can = 0;

        // 初始化出边数组
        for (int i=0; i<prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            if (map.get(b) == null) {
                map.put(b, new ArrayList<>());
            }
            map.get(b).add(a);
            in[a]++;
        }

        // 度为0的课放入栈
        for (int i=0; i<in.length; i++) {
            if (in[i] == 0) {
                stack.add(i);
            }
        }

        while (stack.size() != 0) {
            int size = stack.size();
            for (int i=0; i<size; i++) {
                // 栈顶出栈
                int num = stack.removeFirst();
                // 加入到结果数组
                res[index++] = num;
                // 度为0则可以学, can+1
                can = in[num] == 0 ? can+1 : can;
                List<Integer> list = map.get(num);
                if (list != null) {
                    for (Integer to : list) {
                        in[to]--;
                        // 度是0的加入到栈中
                        if (in[to] == 0) {
                            stack.add(to);
                        }
                    }
                }
            }
        }

        return can == numCourses ? res : new int[0];
    }
}
