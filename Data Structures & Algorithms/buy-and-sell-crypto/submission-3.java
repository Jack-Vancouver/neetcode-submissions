class Solution {
    public int maxProfit(int[] prices) {
        // 1. 初始化两个变量
        // minPrice 记录我们“历史见过的最低买入价”，初始设为最大值，保证马上会被替换
        int minPrice = Integer.MAX_VALUE; 
        // maxProfit 记录我们的“历史最高利润”
        int maxProfit = 0; 

        // 2. 开始按天数遍历股票
        for (int i = 0; i < prices.length; i++) {
            
            if (prices[i] < minPrice) {
                // 如果今天的价格比我之前见过的所有价格都低，那今天就是绝佳的买入点！
                // 更新历史最低价。注意：今天刚买，今天肯定不卖，所以不计算利润。
                minPrice = prices[i];
                
            } else if (prices[i] - minPrice > maxProfit) {
                // 如果今天不是历史最低价，那说明股票涨了！
                // 算一算：如果我以【历史最低价】买入，以【今天】的价格卖出，能赚多少？
                // 如果赚的钱比我之前的最高记录还多，就更新我的最高利润记录！
                maxProfit = prices[i] - minPrice;
            }
        }
        
        // 3. 遍历完所有的日子，交出最高利润
        return maxProfit;
    }
}