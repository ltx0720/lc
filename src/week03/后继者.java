package week03;

import common.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 后继者 {

    private List<TreeNode> list;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        return getNext(root, p.val);
    }

    private TreeNode getNext(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        TreeNode ans = null;
        while (root != null) {
            if (root.val == val) {
                if (root.right != null) {
                    ans = root.right;
                    while (ans.left != null) {
                        ans = ans.left;
                    }
                }
                return ans;
            }

            if (val < root.val) {
                ans = root;
                root = root.left;
            }

            if (root.val < val) {
                root = root.right;
            }
        }
        return ans;
    }


//    /**
//     * 中序遍历, 在结果数组中遍历查找
//     */
//    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        list = new ArrayList<>();
//        inorder(root);
//
//        int index = -1;
//        for (int i=0; i<list.size(); i++) {
//            if (list.get(i).val == p.val) {
//                index = i;
//                break;
//            }
//        }
//
//        if (index == -1 || index >= list.size()-1) {
//            return null;
//        }
//
//        return list.get(index);
//    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root);
        inorder(root.right);
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            TreeNode ret = new 后继者().inorderSuccessor(root, new TreeNode(1));

            String out = treeNodeToString(ret);

            System.out.print(out);
        }
    }
}
