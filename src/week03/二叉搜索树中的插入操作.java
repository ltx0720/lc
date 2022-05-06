package week03;

import common.TreeNode;

public class 二叉搜索树中的插入操作 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        doInsert(root, val);
        return root;
    }

    private void doInsert(TreeNode node, int val) {
        if (val < node.val) {
            if (node.left != null) {
                doInsert(node.left, val);
            } else {
                node.left = new TreeNode(val);
            }
        } else {
            if (node.right != null) {
                doInsert(node.right, val);
            } else {
                node.right = new TreeNode(val);
            }
        }
    }
}
