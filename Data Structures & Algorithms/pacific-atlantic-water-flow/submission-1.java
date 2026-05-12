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
        // 逻辑：既然是两支舰队分别向内陆进发，我们就需要两个跟地图一样大的小本本。
        // 太平洋舰队淹过的地方，就在 canReachPacific 里打个 true；大西洋舰队同理。互不干扰。
        boolean[][] canReachPacific = new boolean[rows][cols];
        boolean[][] canReachAtlantic = new boolean[rows][cols];

        // 1. 顺着海岸线，派出舰队“逆流而上”
        // 逻辑：把四个边缘的格子全部作为起点，呼叫 dfs 舰队开始往内陆爬。
        // 太平洋占领了上和左，所以它从 r=0 (上) 和 c=0 (左) 出发。
        // 大西洋占领了下和右，所以它从 rows-1 (下) 和 cols-1 (右) 出发。

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
        // 逻辑：等两个大洋的舰队都爬完山、做完标记后。
        // 我们只需要遍历一次地图，看看哪个格子的两个本本上都是 true，把它装进结果里就行了。
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
        // 因为我们是倒灌（逆流），水只能往更高的地方流（或者平地）。
        // 如果前面那座山比我现在踩着的地方还矮，水是灌不上去的，所以直接 return 放弃这条路。
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