/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution {
    public TreeNode invertTree(TreeNode root) {
        // 1. 终止条件：如果节点为空，直接返回空
        if (root == null) {
            return null;
        }

        // 2. 当前节点的核心工作：交换左右手
        // 这跟你交换 a 和 b 是一样的： tmp = a; a = b; b = tmp;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 3. 递归外包：命令刚换到左边的子树去完成内部翻转，
        // 再命令刚换到右边的子树去完成内部翻转。
        invertTree(root.left);
        invertTree(root.right);

        // 返回翻转好的这棵树的根节点
        return root;
    }
}