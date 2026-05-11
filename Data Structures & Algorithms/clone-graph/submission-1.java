/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // 核心神器：全局登记册
    // Key: 老节点, Value: 对应的新节点
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        // Base Case 0: 极端情况防御，给你个空图，你直接返回空
        if (node == null) {
            return null;
        }

        // Base Case 1: 如果这个老节点已经在登记册里了，
        // 说明我们已经克隆过它了，直接把它的克隆体交出去，停止向下递归！
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // --- 下面是处理没见过的全新节点的逻辑 ---

        // 1. 照猫画虎：照着老节点的值，new 一个全新的空壳节点出来
        // (注意：此时先不要管它的邻居，给个空 ArrayList)
        Node cloneNode = new Node(node.val, new ArrayList<>());

        // 2. 【极其致命的关键步】：立刻把它登记造册！
        // 绝对不能等到邻居都克隆完了再登记，否则遇到环直接死循环！
        visited.put(node, cloneNode);

            // 请死死盯住这一句代码：visited.put(node, cloneNode); 必须放在 for 循环（克隆邻居）的绝对前面！
            // 我们慢镜头回放一下如果遇到环（1 和 2 互相连接）：

            // 来到老节点 1。不在登记册里。new 出新节点 1'。
            // 立刻登记：Map 里现在有了 {老1 : 新1'}。
            // 遍历老节点 1 的邻居，发现老 2。
            // 深入递归，来到老节点 2。不在登记册里。new 出新节点 2'。
            // 立刻登记：Map 里现在有了 {老1 : 新1', 老2 : 新2'}。
            // 遍历老节点 2 的邻居，发现老 1！
            // 深入递归，来到老节点 1。触发 Aha! 时刻：掏出登记册一查，发现老 1 已经在里面了！
            // 直接返回 Map.get(老1)，也就是之前创建的一半的 新1'。死循环被完美掐断！

            // 如果你把登记操作放在 for 循环的后面，当代码试图去克隆老 2 的邻居老 1 时，登记册里根本没有老 1，它又会去 new 一个新 1，然后继续找邻居……系统当场崩溃。

        // 3. 递归外包：老节点有几个邻居，我们就派 DFS 去克隆几个邻居
        for (Node neighbor : node.neighbors) {
            // 把克隆回来的邻居新节点，加到当前克隆节点的邻居列表里
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        // 4. 完美组装完毕，交差！
        return cloneNode;
    }
}