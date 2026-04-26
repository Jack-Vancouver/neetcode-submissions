class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        // 1. 遍历棋盘的每一个格子，把它当作寻宝的起点
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 只要有任何一个起点能找到完整的 word，就直接成功下班！
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 核心质检员：当前在 (r, c) 位置，正在寻找 word 的第 index 个字符
    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base Case 1：全都找完了！(index 越界说明所有字母都匹配成功了)
        if (index == word.length()) {
            return true;
        }

        // Base Case 2：撞墙了，或者找错人了
        // 1. 越界了
        // 2. 当前格子的字母，和我们要找的字母不一样
        // 3. 踩到了 '#'，说明这是当前路径刚走过的死路
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length 
            || board[r][c] != word.charAt(index)) {
            return false;
        }

        // --- 下面是回溯算法的标准模板 ---

        // 1. 记录原来的真实字母
        char temp = board[r][c];
        
        // 2. 做选择（隐身术）：标记为已走过，防止自己人踩自己人
        board[r][c] = '#';

        // 3. 递归：向上下左右四个方向疯狂试探（只要有一条路通了就行！）
        boolean found = dfs(board, word, r + 1, c, index + 1) || // 往下走
                        dfs(board, word, r - 1, c, index + 1) || // 往上走
                        dfs(board, word, r, c + 1, index + 1) || // 往右走
                        dfs(board, word, r, c - 1, index + 1);   // 往左走

        // 4. 撤销选择（时光倒流）：不管刚刚那四条路通没通，我都要把现场恢复原样！
        // 因为如果当前路径失败了，其他从别的起点过来的路径可能还需要用这个字母。
        board[r][c] = temp;

        // 返回试探的结果
        return found;
    }
}