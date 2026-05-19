class Solution {
    public int coinChange(int[] coins, int amount) {
        // 1. 初始化 DP 数组，长度为 amount + 1
        // dp[i] 代表凑齐金额 i 所需要的最少硬币数
        int[] dp = new int[amount + 1];
        
        // 2. 【核心细节】：将数组用一个“极大值”填满
        // 为什么不用 Integer.MAX_VALUE？
        // 因为后面有 dp[i - coin] + 1 的操作。如果值是 MAX_VALUE，加 1 就会溢出变成负数！
        // 为什么用 amount + 1？
        // 因为就算全用 1 块钱的硬币，最多也只需要 amount 个硬币。
        // 所以 amount + 1 在物理意义上就是一个绝对达不到的“无穷大”。
        Arrays.fill(dp, amount + 1);
        
        // 3. Base Case: 凑齐金额 0，需要 0 个硬币
        dp[0] = 0;
        
        // 4. 开始从小到大，把每一个金额的最优解算出来 (自底向上)
        for (int i = 1; i <= amount; i++) {
            
            // 尝试手里的每一种硬币
            for (int coin : coins) {
                // 前提是：当前这个硬币的面值，不能比我要凑的金额还大
                if (i >= coin) {
                    // 状态转移：当前最优解 vs (用这个硬币凑齐前置金额的最优解 + 1个当前硬币)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // 5. 结算：如果最后的金额依然是那个“无穷大”，说明没有任何一种组合能凑出这个金额
        return dp[amount] > amount ? -1 : dp[amount];
    }
}