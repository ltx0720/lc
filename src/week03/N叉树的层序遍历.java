package week03;

import common.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N叉树的层序遍历 {

    public List<List<Integer>> levelOrder(Node root) {
        LinkedList<Node> list = new LinkedList<>();
        List<List<Integer>> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        list.add(root);
        while (list.size() != 0) {
            int size = list.size();
            List<Integer> sonList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node head = list.removeFirst();
                sonList.add(head.val);
                list.addAll(head.children);
            }
            resList.add(sonList);
        }
        return resList;
    }
}
