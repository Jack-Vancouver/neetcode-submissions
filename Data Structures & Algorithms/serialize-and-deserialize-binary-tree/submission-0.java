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

public class Codec {

    // 分隔符和空节点标记
    private static final String SPLITTER = ",";
    private static final String NN = "X"; // 用 "X" 代表 null，省空间

    // ==========================================
    // 1. 序列化：树 -> 字符串
    // ==========================================
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    // 辅助函数：前序遍历（根 -> 左 -> 右）
    private void buildString(TreeNode node, StringBuilder sb) {
        // Base Case：遇到空节点，写入 "X,"
        if (node == null) {
            sb.append(NN).append(SPLITTER);
            return;
        }
        // 根：写入当前节点的值，比如 "1,"
        sb.append(node.val).append(SPLITTER);
        
        // 左：递归处理左子树
        buildString(node.left, sb);
        
        // 右：递归处理右子树
        buildString(node.right, sb);
    }

    // ==========================================
    // 2. 反序列化：字符串 -> 树
    // ==========================================
    public TreeNode deserialize(String data) {
        // 先把字符串用逗号切开，变成一个队列 (Queue)
        // 比如 "1,2,X,X,3,X,X" 变成队列 [1, 2, X, X, 3, X, X]
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(SPLITTER)));
        return buildTree(nodes);
    }

    // 辅助函数：吃掉队列里的元素，重组大树
    private TreeNode buildTree(Queue<String> nodes) {
        // 队列最前面的，就是当前范围的根节点！拿出来看看
        String val = nodes.poll();
        
        // 如果拿到的是空标记 "X"，说明走到头了，返回 null
        if (val.equals(NN)) {
            return null;
        }
        
        // 否则，这就是个实打实的节点。把它 new 出来！
        TreeNode node = new TreeNode(Integer.parseInt(val));
        
        // 按照前序遍历的规矩，接下来排队的，绝对是左子树的人！
        node.left = buildTree(nodes);
        
        // 左子树的人都被上面那句代码吃光之后，剩下的绝对是右子树的人！
        node.right = buildTree(nodes);
        
        return node;
    }
}