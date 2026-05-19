class Solution {
    public int rob(int[] nums) {
        // 0. 极端情况防御
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // prev2 代表走到 i-2 个房子时的最高收益
        int prev2 = 0; 
        
        // prev1 代表走到 i-1 个房子时的最高收益
        int prev1 = 0; 
        
        // current 代表走到当前第 i 个房子时的最高收益
        int current = 0; 

        // 挨个房子扫过去
        for (int num : nums) {
            // 【核心状态转移方程】：不偷当前房子 vs 偷当前房子
            current = Math.max(prev1, prev2 + num);
            
            // 【灵魂操作：毛毛虫向前滚动】
            // 准备去下一个房子了，历史数据集体往前挪一步
            prev2 = prev1;   // 老的 prev1 变成了新的 prev2
            prev1 = current; // 刚算出的 current 变成了新的 prev1
        }

        // 扫完所有房子，prev1 (也就是最后的 current) 就是最高总收益
        return current;
    }
}


    //我们拿数组 [2, 7, 9, 3, 1] 来慢镜头推演一下：
    //current = Math.max(prev1, prev2 + num);
    //
    //初始状态：prev2 = 0, prev1 = 0。
    //
    //来到 2：current = max(0, 0 + 2) = 2。滚动：prev2 = 0, prev1 = 2。
    //
    //来到 7：current = max(2, 0 + 7) = 7。滚动：prev2 = 2, prev1 = 7。
    //
    //来到 9：current = max(7, 2 + 9) = 11。滚动：prev2 = 7, prev1 = 11。
    //(注：这里展现了 DP 的威力！系统发现跳过 7，选择 2+9 赚得更多！)
    //
    //来到 3：current = max(11, 7 + 3) = 11。滚动：prev2 = 11, prev1 = 11。
    //(注：这里发现加上 3 还不如保留之前的 11 赚得多，所以果断放过 3！)
    //
    //来到 1：current = max(11, 11 + 1) = 12。滚动：prev2 = 11, prev1 = 12。
    //
    //最终答案是 12（偷 2，9，1）。