class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        
        // 0. 极端情况防御：只有 1 个房子，直接偷了跑路，因为不存在首尾相连的问题
        if (n == 1) {
            return nums[0];
        }
        // 只有 2 个房子，首尾相连了，只能二选一偷最大的
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }

        // 1. 宇宙 A：剥夺最后一个房子的资格，抢夺范围 [0, n - 2]
        int maxUniverseA = robRange(nums, 0, n - 2);

        // 2. 宇宙 B：剥夺第一个房子的资格，抢夺范围 [1, n - 1]
        int maxUniverseB = robRange(nums, 1, n - 1);

        // 3. 对决：取两个平行宇宙里的最大值
        return Math.max(maxUniverseA, maxUniverseB);
    }

    // --- 核心辅助函数：上一道题 (House Robber I) 的原版代码 ---
    // 在指定的数组范围 [start, end] 内，求最大线性抢劫收益
    private int robRange(int[] nums, int start, int end) {
        int prev2 = 0; // i-2 的最高收益
        int prev1 = 0; // i-1 的最高收益
        int current = 0;

        for (int i = start; i <= end; i++) {
            // 状态转移方程：不偷当前 vs 偷当前
            current = Math.max(prev1, prev2 + nums[i]);
            
            // 毛毛虫滚动
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
}