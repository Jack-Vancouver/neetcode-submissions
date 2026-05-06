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