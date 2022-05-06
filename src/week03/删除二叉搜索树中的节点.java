package week03;

import common.TreeNode;

public class 删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // 后继
            TreeNode afterNode = root.right;
            while (afterNode.left != null) {
                afterNode = afterNode.left;
            }
            root.right = deleteNode(root.right, afterNode.val);
            root.val = afterNode.val;
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

}
