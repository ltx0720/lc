package week03;

import java.util.*;

public class 课程表 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) {
            return true;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] in = new int[numCourses];
        LinkedList<Integer> stack = new LinkedList<>();
        int can = 0;

        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            map.computeIfAbsent(b, k -> new ArrayList<>());
            map.get(b).add(a);
            in[a]++;
        }

        for (int i=0; i<in.length; i++) {
            if (in[i] == 0) {
                stack.add(i);
            }
        }

        while (stack.size() != 0) {
            int size = stack.size();
            for (int i=0; i<size; i++) {
                int num = stack.removeFirst();
                can = in[num] == 0 ? can+1 : can;
                List<Integer> list = map.get(num);
                if (list != null) {
                    for (Integer to : list) {
                        in[to]--;
                        if (in[to] == 0) {
                            stack.add(to);
                        }
                    }
                }
            }
        }

        return can == numCourses;
    }
}
