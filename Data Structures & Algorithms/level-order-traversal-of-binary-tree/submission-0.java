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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 最终的档案大抽屉，用来装每一层的小本子
        List<List<Integer>> result = new ArrayList<>();
        
        // 0. 极端情况防御
        if (root == null) {
            return result;
        }
        
        // 1. 建立排队大厅，老大先进去
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // 2. 只要大厅里还有人，说明还有楼层没遍历完
        while (!queue.isEmpty()) {
            
            // 【灵魂微操：保安锁门快照！】
            // 死死盯住当前这一层到底有几个人
            int levelSize = queue.size();
            
            // 发一个当前楼层专属的小本子
            List<Integer> currentLevel = new ArrayList<>();
            
            // 3. 严格按照刚才记录的人数，把他们叫出来
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll(); // 出列
                
                // 把数字记在小本子上
                currentLevel.add(current.val);
                
                // 把他们的直系下属拉进大厅排队（为下一层做准备）
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            // 4. 当前这一个 for 循环结束，意味着这一层已经全部登记完毕
            // 把这层的小本子，正式归档到大抽屉里
            result.add(currentLevel);
        }
        
        return result;
    }
}