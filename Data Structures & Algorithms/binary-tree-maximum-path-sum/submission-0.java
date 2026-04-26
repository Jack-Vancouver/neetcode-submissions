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
    // 1. 建立一个全局变量，用来实时记录我们在任何地方发现的“最大拱桥和”
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // 核心函数：计算该节点能给父节点提供的“最大单边贡献”
    private int maxGain(TreeNode node) {
        // Base Case: 空节点贡献为 0
        if (node == null) {
            return 0;
        }

        // 2. 递归计算左右子树提供的贡献
        // 【核心细节】：如果贡献是负的，我们就不要了（Math.max(0, ...)），及时止损！
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // 3. 【角色 A】：尝试以此节点为“最高点（拱桥）”形成完整路径
        // 这个路径和就是：当前值 + 左边给的 + 右边给的
        int currentPathSum = node.val + leftGain + rightGain;

        // 更新全局最大值
        maxSum = Math.max(maxSum, currentPathSum);

        // 4. 【角色 B】：向上级汇报（回溯）
        // 只能挑一条最长的路汇报给父节点：当前值 + 左右两边更强的那一个
        return node.val + Math.max(leftGain, rightGain);
    }
}