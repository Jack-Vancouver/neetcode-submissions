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

class Solution {
    // 【主函数：巡视员】负责在大树里到处找
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 1. 防御防线：大树都找空了，还没找到，肯定是没有了
        if (root == null) {
            return false;
        }
        
        // 2. 核心比对：拿着大树的当前节点，跟小树比一比。一样吗？
        if (isSameTree(root, subRoot)) {
            return true; // 一模一样，直接下班！
        }
        
        // 3. 继续巡视：当前节点不是，那就去左边分支找，或者去右边分支找。
        // 只要有一边能找到，整个结果就是 true！所以用 || (或者)
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // 【辅助函数：质检员】负责死磕两个节点是不是一模一样 (上一题的源码，一字不改)
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}