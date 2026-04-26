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
    // 主函数
    public boolean isValidBST(TreeNode root) {
        // 初始状态，根节点没有任何约束，用 Long 的最大最小值代表正负无穷
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 辅助质检员：不仅要看当前节点，还要看它是否在这个区间内
    private boolean isValidBSTHelper(TreeNode node, long min, long max) {
        // 1. 走到空节点，说明这条路上的祖祖辈辈都没违规，返回 true
        if (node == null) {
            return true;
        }

        // 2. 紧箍咒发威：如果当前节点的值触碰或超出了边界，直接抓捕！
        // 注意：BST 里不能有相等的值，所以必须是 <= 或 >=
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // 3. 递归检查左右子树，同时【收紧边界】
        // 去左边：最大值不能超过我当前的值 (node.val)
        boolean isLeftValid = isValidBSTHelper(node.left, min, node.val);
        // 去右边：最小值不能低于我当前的值 (node.val)
        boolean isRightValid = isValidBSTHelper(node.right, node.val, max);

        // 左边右边都合法，这棵树才合法
        return isLeftValid && isRightValid;
    }
}