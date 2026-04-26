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
    public int maxDepth(TreeNode root) {
        // 1. Base Case (终止条件)：
        // 如果当前节点是空的，说明这下面没有任何层数了，深度为 0
        if (root == null) {
            return 0;
        }

        // 2. 向下派发任务：
        // 让左子树去算它的最大深度
        int leftDepth = maxDepth(root.left);
        // 让右子树去算它的最大深度
        int rightDepth = maxDepth(root.right);

        // 3. 汇总结果：
        // 比较左右两边谁更深，取最大值，然后加上当前节点所在的这 1 层
        return Math.max(leftDepth, rightDepth) + 1;
    }
}

//最优解 1：递归法 DFS（算法理论上的最优解）
//这是大厂白板面试中最标准的答案，代码极度优美，完美契合了二叉树的数学递归定义。
//核心物理图像（分治思维）：
//假设你是这棵树的根节点（大老板），你想知道你的公司到底有几个层级。你不需要自己跑去基层数，你只需要：
//1 打电话问你的左手副总（左子树）：“你的团队最深有几层？”
//2 打电话问你的右手副总（右子树）：“你的团队最深有几层？”
//3 你把他们俩汇报上来的数字取一个最大值，然后加上你自己这一层（+1），这就是全公司的最大深度！

//时间复杂度：O(N)。树上的每个节点都会被严格访问一次。
//空间复杂度：O(H)，其中 H 是树的高度。这是由于系统调用栈的消耗。最坏情况（树退化成链表）是 O(N)，最好情况（完美平衡树）是 O(log N)。