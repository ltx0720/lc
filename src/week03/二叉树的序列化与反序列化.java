package week03;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class 二叉树的序列化与反序列化 {
    private final static String NULL = "null";

    private final static String SPLIT = ",";


    public String serialize(TreeNode root) {
        return level(root);
    }

    public TreeNode deserialize(String data) {
        return buildTree(data);
    }


    /**
     * 层序遍历
     */
    private String level(TreeNode root) {
        if (root == null) {
            return "";
        }

        List<String> resList = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        resList.add(root.val + "");

        // 每层处理
        while (list.size() > 0) {
            // 该层的个数
            int size = list.size();
            for (int i=0; i<size; i++) {
                TreeNode node = list.removeFirst();

                // 左子树
                if (node.left != null) {
                    list.add(node.left);
                    resList.add(node.left.val + "");
                } else {
                    resList.add(NULL);
                }

                // 右子树
                if (node.right != null) {
                    list.add(node.right);
                    resList.add(node.right.val + "");
                } else {
                    resList.add(NULL);
                }
            }
        }
        return String.join(SPLIT, resList);
    }

    private TreeNode buildTree(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        List<String> list = Arrays.stream(data.split(SPLIT)).collect(Collectors.toList());
        return doBuildTree(new LinkedList(list));
    }

    private TreeNode doBuildTree(LinkedList<String> dataList) {
        // 每层需要处理的节点
        LinkedList<TreeNode> levelList = new LinkedList<>();

        // 初始化根节点
        TreeNode root = new TreeNode(Integer.parseInt(dataList.removeFirst()));
        levelList.add(root);

        while (levelList.size() > 0) {
            int size = levelList.size();

            // 遍历该层的节点
            for (int i=0; i<size; i++) {
                TreeNode node = levelList.removeFirst();

                // 构建左子树
                if (dataList.size() > 0) {
                    String l = dataList.removeFirst();
                    if (NULL.equals(l)) {
                        node.left = null;
                    } else {
                        TreeNode left = new TreeNode(Integer.parseInt(l));
                        node.left = left;
                        levelList.add(left);
                    }
                }

                // 构建右子树
                if (dataList.size() > 0) {
                    String r = dataList.removeFirst();
                    if (NULL.equals(r)) {
                        node.right = null;
                    } else {
                        TreeNode right = new TreeNode(Integer.parseInt(r));
                        node.right = right;
                        levelList.add(right);
                    }
                }
            }
        }
        return root;
    }
}
