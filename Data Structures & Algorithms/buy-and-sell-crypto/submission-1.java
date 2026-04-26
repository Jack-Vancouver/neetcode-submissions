class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0; // 应对空数组或只有1天的极端情况

        int buy = 0;
        int in = 0; // 0 表示空仓，1 表示已买入
        int maxProfit = 0; // 【核心修正】：用最高利润代替 sell 变量

        // 遍历每一天（注意这里可以遍历到最后一天了）
        for(int i = 0; i < length; i++) {
            
            // ==========================================
            // 第一步：判断买入或更新底价 (你的原始逻辑)
            // ==========================================
            if (in == 0) {
                // 【设置 buy 点】：如果还没买，且今天比明天便宜（有上涨趋势），果断买入！
                if (i < length - 1 && prices[i] < prices[i+1]) {
                    buy = prices[i];
                    in = 1; // 状态切换为已买入
                }
            } else {
                // 【更新 buy 点】：如果已经买了，但发现今天的价格竟然比我当初买的还低！
                // 那我就“假装”是今天刚买的（把成本价拉低），这样未来可能赚得更多。
                if (prices[i] < buy) {
                    buy = prices[i];
                }
            }

            // ==========================================
            // 第二步：判断卖出利润 (修正后的 sell 逻辑)
            // ==========================================
            if (in != 0) {
                // 只要我已经持仓了，我每天都算一笔账：如果今天卖掉，能赚多少？
                int currentProfit = prices[i] - buy;
                
                // 如果今天卖出的利润，打破了历史记录，就更新我的最高利润！
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }
        }
        
        // 带着最高利润下班！
        return maxProfit;
    }
}