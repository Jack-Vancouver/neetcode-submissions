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

        // 3. 递归外包：老节点有几个邻居，我们就派 DFS 去克隆几个邻居
        for (Node neighbor : node.neighbors) {
            // 把克隆回来的邻居新节点，加到当前克隆节点的邻居列表里
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        // 4. 完美组装完毕，交差！
        return cloneNode;
    }
}