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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 第一道防线：两个节点同时为空。
        // 这意味着我们顺着分支走到了尽头，并且两边都是同时没路的，说明完全匹配，返回 true。
        if (p == null && q == null) {
            return true;
        }
        
        // 第二道防线：一个为空，另一个不为空。
        // 走到这里，说明上面那个 if 没拦截住（说明必定至少有一个不为空）。
        // 如果此时有一个为空，说明两棵树的“骨架”不一样，直接宣判死刑，返回 false。
        if (p == null || q == null) {
            return false;
        }
        
        // 第三道防线：骨架一样，但里面的数字不一样。
        // 比如一个是 2，一个是 3。血肉不合，直接宣判死刑，返回 false。
        if (p.val != q.val) {
            return false;
        }
        
        // 闯过前面三道防线，说明【当前这俩节点】完全一模一样！
        // 接下来，不仅当前要一样，还要保证他俩的左子树一样，并且右子树也一样。
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}