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
        // 🔪 第一层（最里面）：data.split(SPLITTER)
        // •	动作：字符串切割。
        // •	解释：data 就是我们传进来的长字符串，比如 "1,2,X,X,3,X,X"。.split(",") 的作用是根据逗号把这个长字符串切碎。
        // •	结果：切碎后，它变成了一个普通的 Java 字符串数组（Array）：["1", "2", "X", "X", "3", "X", "X"]。
        // 🔪 第二层：Arrays.asList(...)
        // •	动作：数组转列表。
        // •	解释：上一步我们得到了一个普通的数组。但是，Java 里的队列（LinkedList）是不吃“普通数组”的，它只吃“集合（Collection）”。
        // •	Arrays.asList() 是 Java 提供的一个工具箱，专门用来把死板的数组包装成灵活的列表（List）。
        // •	结果：刚才的数组被包装成了一个 List<String>。
        // 🔪 第三层：new LinkedList<>(...)
        // •	动作：创建链表（队列的具体实现）。
        // •	解释：拿着上一步包装好的 List，我们把它当作初始材料，倒进一个全新创建的 LinkedList（链表）里。
        // •	结果：现在我们在内存里真实地造出了一个装满这些字符串零件的 LinkedList 对象。
        // 🔪 第四层（最外面）：Queue<String> nodes = ...
        // •	动作：声明变量并赋值。
        // •	解释：我们给刚才造出来的 LinkedList 贴上了一个标签，名字叫 nodes。并且声明它的类型是 Queue<String>（一个专门装字符串的队列）。
        
        // 🧠 核心 Java 语法补充：为什么是 Queue = new LinkedList？
        // 你可能会觉得奇怪：既然我造的是 LinkedList，为什么左边的类型写的是 Queue？
        // 为什么不写成 LinkedList<String> nodes = new LinkedList<>(...)？
        // 这涉及 Java 里一个非常核心的语法概念：接口（Interface）与实现（Implementation）。
        // •	Queue 是接口（定规矩的）： 它是老板，只负责提要求。它说：“我需要一个能排队的东西，必须能排在队尾，能从队头拿走，先进先出。” 但 Queue 自己不知道怎么干活。
        // •	LinkedList 是实现类（干活的）： 它是打工人，具体实现了怎么排队、怎么增删数据。
        // 写成 Queue<String> nodes = new LinkedList<>(); 的好处是（这叫“面向接口编程”）：
        // 我们在用 nodes 这个变量时，只能调用老板 Queue 允许的方法（比如 offer() 进队，poll() 出队）。
        // 这限制了我们不能乱用 LinkedList 其他花里胡哨的功能，保证了这个变量严格按照“队列（先进先出）”的逻辑工作。
        // 这在大型项目中是为了让代码逻辑更清晰、更不容易出错。
        
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