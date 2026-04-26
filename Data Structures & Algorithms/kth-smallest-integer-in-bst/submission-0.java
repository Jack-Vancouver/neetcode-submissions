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
    public int kthSmallest(TreeNode root, int k) {
        // 准备一个手动的栈，用来模拟系统递归的回溯过程
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        int count = 0; // 计数器

        // 只要节点还没走完，或者栈里还有人，就继续
        while (curr != null || !stack.isEmpty()) {
            
            // 1. 疯狂往左走，走到死胡同为止！
            // 因为最左边的人一定是在这个分支里最小的
            while (curr != null) {
                stack.push(curr); // 把路过的人都压进栈里，等下要回头找他们
                curr = curr.left;
            }

            // 2. 走到头了，从栈顶弹出一个节点
            // 这是目前能找到的最小的人
            curr = stack.pop();
            
            // 3. 核心动作：计数！
            count++;
            if (count == k) {
                return curr.val; // 找到第 k 个了，直接下班！
            }

            // 4. 左边和中间都处理完了，现在该去处理右边了
            curr = curr.right;
        }

        return -1; // 理论上永远走不到这里，因为题目保证了 k 永远合法
    }
}