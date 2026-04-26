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
    public TreeNode invertTree(TreeNode root) {
        //如果你面的是资深工程师（Senior SDE）岗位，或者面试官是一个实战经验极其丰富的老兵，
        //他会告诉你：在真实的生产环境中，递归解法极其危险，它不是工程意义上的最优解。
        
        // 1. 极端情况防御
        if (root == null) {
            return null;
        }

        // 2. 创建一个排队大厅 (Queue)，使用 LinkedList 实现
        Queue<TreeNode> queue = new LinkedList<>();
        
        // 3. 根节点先去排队
        queue.offer(root);

        // 4. 只要大厅里还有人排队，就继续处理
        while (!queue.isEmpty()) {
            // 最前面的人出队
            TreeNode current = queue.poll();

            // 他的核心任务：互换自己的左右子树
            TreeNode tmp = current.left;
            current.left = current.right;
            current.right = tmp;

            // 互换完之后，如果他的子节点不为空，把子节点也塞进队尾排队
            // 注意：此时 current.left 已经是原来的右子树了，但没关系，都要排队
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return root;
    }
}