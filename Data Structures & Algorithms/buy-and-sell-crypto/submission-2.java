public class Solution {
    public int maxProfit(int[] prices) {
        //传入一个空的数组 prices = [] 时
        if (prices == null || prices.length == 0) return 0;
        
        int maxP = 0;              // 历史最高利润记录
        int minBuy = prices[0];    // 历史最低买入价（一上来先假设第一天买入）

        // 【神来之笔】：直接用 for-each 循环遍历每一天的价格
        // 把当前遍历到的每一天，都假设为“卖出日” (sell)
        for (int sell : prices) {
            
            // 动作 1：算利润
            // 假设我今天卖出 (sell)，减去我之前记下的最低买入价 (minBuy)，能赚多少？
            // 赚得比历史最高记录多，就更新 maxP。
            maxP = Math.max(maxP, sell - minBuy);
            
            // 动作 2：找底价
            // 顺便看看今天的价格，是不是比我历史记录里的最低价还要低？
            // 如果是，就把今天当作未来日子的新“最低买入价”。
            minBuy = Math.min(minBuy, sell);
        }
        return maxP;
    }
}