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
        // 极端情况防御
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        //在 Java 的 Queue（队列）接口里，offer() 的意思就是“入队”（排到队伍的最后面）。
        //add 和 offer的区别：
        //add(E e)：如果队列满了，塞不进去了，它会抛出异常 (IllegalStateException)，直接让程序崩溃。这是比较暴力的做法。
        //offer(E e)：如果队列满了，它不会报错，而是温和地返回一个 false，告诉你“添加失败了”。

        queue.offer(root); // 根节点先进大厅排队
        
        int depth = 0; // 记录楼层（深度）

        // 只要大厅里还有人，说明还有楼层没数完
        while (!queue.isEmpty()) {
            // 【核心动作】：看一眼当前这一层总共有多少个人排队
            int levelSize = queue.size();
            
            // 把当前这一层的所有人都赶出大厅，并把他们的直系下属（下一层）全拉进来
            for (int i = 0; i < levelSize; i++) {
                // queue.poll() ：把队伍最前面的人叫出来（返回当前节点地址），并且他在queue里消失了
                TreeNode current = queue.poll();
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            // 这一层的人全部处理完了，楼层总数 +1
            depth++;
        }

        return depth;
    }
}

//最优解 2：迭代法 BFS（工程实践上的最优解）
//结合我们上一题（翻转二叉树）讲过的知识，如果面试官问你：“这棵树非常深，递归会爆栈（StackOverflow），怎么用迭代解决？”

//这时候你就可以直接祭出 Queue（队列）进行层序遍历（BFS）。
//逻辑极其简单：大厅排队，一层一层地数！只要把这一层的人全部清空，深度就加 1。

//时间复杂度：O(N)。每个节点进队出队一次。
//空间复杂度：O(W)，其中 W 是树的最大宽度（某一层最多包含的节点数）。最坏情况下，队列里会装下最后一层的所有节点，占用 O(N) 的堆内存。它彻底避免了栈溢出的风险。