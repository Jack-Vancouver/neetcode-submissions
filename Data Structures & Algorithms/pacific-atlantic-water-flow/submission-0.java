class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        // 0. 极端情况防御
        if (heights == null || heights.length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // 这两个 boolean 矩阵就是我们的“标记册”
        boolean[][] canReachPacific = new boolean[rows][cols];
        boolean[][] canReachAtlantic = new boolean[rows][cols];

        // 1. 顺着海岸线，派出舰队“逆流而上”
        // 先走第一列和最后一列（左右海岸线）
        for (int r = 0; r < rows; r++) {
            // 从左边缘出发，往上爬，标记太平洋
            dfs(heights, r, 0, canReachPacific, heights[r][0]);
            // 从右边缘出发，往上爬，标记大西洋
            dfs(heights, r, cols - 1, canReachAtlantic, heights[r][cols - 1]);
        }

        // 再走第一行和最后一行（上下海岸线）
        for (int c = 0; c < cols; c++) {
            // 从上边缘出发，往上爬，标记太平洋
            dfs(heights, 0, c, canReachPacific, heights[0][c]);
            // 从下边缘出发，往上爬，标记大西洋
            dfs(heights, rows - 1, c, canReachAtlantic, heights[rows - 1][c]);
        }

        // 2. 扫描全图，寻找交集
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 如果这个格子既能被太平洋的海水淹没，也能被大西洋的海水淹没
                if (canReachPacific[r][c] && canReachAtlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    // --- 核心辅助函数：逆流 DFS ---
    // prevHeight 是前一个格子（下游）的高度。我们要往上爬，当前格子必须比 prevHeight 高或相等。
    private void dfs(int[][] heights, int r, int c, boolean[][] visited, int prevHeight) {
        // Base Case：
        // 1. 越界了
        // 2. 这个格子已经被当前舰队访问过了（防止死循环）
        // 3. 【核心物理法则】：当前格子比下游还矮，水是倒灌不上来的！直接 return
        if (r < 0 || c < 0 || r >= heights.length || c >= heights[0].length 
            || visited[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        // 成功逆流到了这个格子，打上标记
        visited[r][c] = true;

        // 以当前格子为新的起点，继续向上下左右尝试逆流
        dfs(heights, r + 1, c, visited, heights[r][c]); // 往下
        dfs(heights, r - 1, c, visited, heights[r][c]); // 往上
        dfs(heights, r, c + 1, visited, heights[r][c]); // 往右
        dfs(heights, r, c - 1, visited, heights[r][c]); // 往左
    }
}