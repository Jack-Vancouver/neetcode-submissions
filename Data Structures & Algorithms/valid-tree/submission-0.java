class Solution {
    public boolean validTree(int n, int[][] edges) {
        // 1. 终极数学捷径：边数不对，绝对不是树
        if (edges.length != n - 1) {
            return false;
        }

        // 2. 初始化并查集 (每个人一开始都是自己的老大)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 3. 遍历每一条边，进行帮派合并
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            // 查：找到这两个节点各自的终极老大
            int root1 = find(parent, node1);
            int root2 = find(parent, node2);

            // 如果终极老大是同一个人，说明加上这条边就成环了！死刑！
            if (root1 == root2) {
                return false;
            }

            // 并：老大不同，则结盟合并（让 root1 的老大变成 root2）
            parent[root1] = root2;
        }

        // 4. 查完了所有边都没有环，且边数正好是 n - 1，完美通关！
        return true;
    }

    // --- 核心辅助函数：找终极老大 (带路径压缩优化) ---
    private int find(int[] parent, int i) {
        // 如果我的上级不是我自己，说明我上面还有大哥
        if (parent[i] != i) {
            // 【路径压缩神技】：我直接跳过中间的层层小头目，直接拜最高领导为直属大哥！
            // 这样下次再查我的老大时，一步就能查到，极大地加速了后续的查找速度
            parent[i] = find(parent, parent[i]); 
        }
        // 返回我的终极老大
        return parent[i];
    }
}