class Solution {
    public int countComponents(int n, int[][] edges) {
        // 1. 初始化并查集
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 每个人一开始的老大都是自己
        }

        // 2. 初始化连通分量的数量，一开始假设各自为战，共有 n 个
        int count = n;

        // 3. 遍历每一条边，尝试合并
        for (int[] edge : edges) {
            int root1 = find(parent, edge[0]);
            int root2 = find(parent, edge[1]);

            // 如果两个节点分属不同阵营，则合并！
            if (root1 != root2) {
                parent[root1] = root2; // 结盟：让 root1 认 root2 为大哥
                count--; // 【核心微操】：两个帮派变成了一个，总帮派数量减 1
            }
        }

        // 4. 返回最终剩下的帮派数量
        return count;
    }

    // --- 核心辅助函数：找终极老大 (带路径压缩优化) ---
    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            // 路径压缩：直接认最高领导为直属大哥，拍扁树的结构
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }
}