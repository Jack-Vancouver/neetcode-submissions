class Solution {
    public int climbStairs(int n) {
        // Base Cases: 1 阶和 2 阶极其直观，直接返回
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        // prev2 代表往下走两步的台阶 (即 n-2 阶) 的走法数
        int prev2 = 1; 
        
        // prev1 代表往下走一步的台阶 (即 n-1 阶) 的走法数
        int prev1 = 2; 
        
        int current = 0; // 当前所在台阶的走法数

        // 从第 3 阶开始往上爬，一直爬到第 n 阶
        for (int i = 3; i <= n; i++) {
            // 核心状态转移方程：当前走法 = 前一阶走法 + 前两阶走法
            current = prev1 + prev2;
            
            // 【灵魂操作：毛毛虫向前滚动】
            // 我们准备计算下一阶了，所以历史数据要集体往前挪一步
            prev2 = prev1;   // 老的 prev1 变成了新的 prev2
            prev1 = current; // 刚算出来的 current 变成了新的 prev1
        }

        return current;
    }
}