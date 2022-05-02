package week03;

import common.TreeNode;

public class 从中序与后序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return doBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode doBuildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        // 无左子树或右子树的情况处理
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        // 这个区间没有其它的数需要再处理, 即已经处理完
        if (postEnd == postStart) {
            return root;
        }

        // 在中序数组中找root
        int rootIndexIn = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == postorder[postEnd]) {
                rootIndexIn = i;
                break;
            }
        }

        // root左子树的长度
        int ln = rootIndexIn - inStart;
        // postorder中左子树的 end index
        int nextLeftEnd = postStart + ln - 1;

        // 无左子树, 可能 rootIndexIn-1 < 0, 需要特殊处理
        root.left = doBuildTree(inorder, inStart, rootIndexIn - 1, postorder, postStart, nextLeftEnd);
        // 如果没有右子树, rootIndexIn是最后一个则rootIndexIn+1 会溢出, 在开头需要特殊处理
        root.right = doBuildTree(inorder, rootIndexIn + 1, inEnd, postorder, nextLeftEnd + 1, postEnd - 1);

        return root;
    }
}
