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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {

            // ① 一路向左，把路上的节点压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // ② 左边到底了，处理当前节点
            cur = stack.pop();
            res.add(cur.val);

            // ③ 转向右子树
            cur = cur.right;
        }

        return res;
    }
}
