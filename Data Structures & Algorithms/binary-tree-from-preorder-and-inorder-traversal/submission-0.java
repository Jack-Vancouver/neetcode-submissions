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
    // 提前建好中序遍历的“数字 -> 索引”的查字典神器
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        // 把中序遍历的元素和位置存进字典
        for (int i = 0; i < inorder.length; i++) {
            // 【修复 1】：必须是 inorder[i]
            inorderMap.put(inorder[i], i); 
        }
        
        // 开启最高统帅部的建树指令
        return build(preorder, 0, preorder.length - 1, 
                     inorder, 0, inorder.length - 1);
    }

    // 【核心大招】：在指定的数组范围内，建立一棵树
    private TreeNode build(int[] preorder, int preStart, int preEnd, 
                           int[] inorder, int inStart, int inEnd) {
        
        // 1. 递归的终止条件：如果给的范围已经错乱（说明这里没有任何节点了），返回 null
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // 2. 找老大：前序遍历的当前范围的第一个元素，就是根节点！
        // 【修复 2】：必须是 preorder[preStart]
        int rootVal = preorder[preStart]; 
        TreeNode root = new TreeNode(rootVal);

        // 3. 切割分家：去字典里查一下，这个老大在中序数组里的索引是几？
        int rootIndexInInorder = inorderMap.get(rootVal);

        // 4. 算人头：老大的左边有多少个兵？
        // 左边兵的数量 = 老大在中序的索引 - 中序当前的起点
        int leftSubTreeSize = rootIndexInInorder - inStart;

        // 5. 递归外包：把大部队劈开，分别交给左右手去建树
        
        // 左子树的指令：
        // 前序范围：从老大的下一个开始，往后数 leftSubTreeSize 个人
        // 中序范围：从中序起点开始，到老大的前一个位置
        root.left = build(preorder, preStart + 1, preStart + leftSubTreeSize, 
                          inorder, inStart, rootIndexInInorder - 1);

        // 右子树的指令：
        // 前序范围：越过左子树的所有人，直到最后
        // 中序范围：从老大的后一个位置开始，直到最后
        root.right = build(preorder, preStart + leftSubTreeSize + 1, preEnd, 
                           inorder, rootIndexInInorder + 1, inEnd);

        // 6. 完工，把建好的根节点交上去
        return root;
    }
}