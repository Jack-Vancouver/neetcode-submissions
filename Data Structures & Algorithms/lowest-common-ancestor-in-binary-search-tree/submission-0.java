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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 只要树没走到头，就一直找
        while (root != null) {
            
            // 两人都在当前节点的右边（都比根大），说明祖先在右子树
            if (p.val > root.val && q.val > root.val) {
                root = root.right; // 往右走
            }
            // 两人都在当前节点的左边（都比根小），说明祖先在左子树
            else if (p.val < root.val && q.val < root.val) {
                root = root.left;  // 往左走
            }
            // 两人一左一右，或者其中一个人就是当前的 root！
            // 说明我们找到了“分叉点”，这个 root 就是最低公共祖先！
            else {
                return root;
            }
        }
        
        return null; // 理论上 BST 里如果 p 和 q 都存在，绝不会走到这里
    }
}