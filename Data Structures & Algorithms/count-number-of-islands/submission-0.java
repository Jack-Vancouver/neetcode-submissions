class Solution {
    public int numIslands(char[][] grid) {
        // 0. 极端情况防御
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int numIslands = 0; // 岛屿计数器

        // 1. 直升机开始全图扫描
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 2. 只有看到 '1' 的时候，才确信发现了新岛屿
                if (grid[r][c] == '1') {
                    numIslands++; // 计数器加 1
                    
                    // 3. 立刻空投拆迁队，把这整座岛全炸平（全变成 '0'）
                    dfs(grid, r, c);
                }
            }
        }

        // 4. 扫描完整个地球，汇报岛屿总数
        return numIslands;
    }

    // --- 辅助函数：DFS 拆迁队 ---
    private void dfs(char[][] grid, int r, int c) {
        // Base Case：撞墙了（越界），或者掉水里了（遇到 '0'），直接停止拆迁并返回
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }

        // 核心微操：把当前脚下的陆地炸平，变成水！
        grid[r][c] = '0';

        // 沿着上下左右四个方向，继续派拆迁队去炸
        dfs(grid, r + 1, c); // 往下
        dfs(grid, r - 1, c); // 往上
        dfs(grid, r, c + 1); // 往右
        dfs(grid, r, c - 1); // 往左
    }
}