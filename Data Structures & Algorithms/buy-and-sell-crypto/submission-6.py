class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        l = 0
        r = 1
        answer = 0

        while r < len(prices):
            if prices[r] > prices[l]:
                # 用之前的最低价买入，今天卖出
                profit = prices[r] - prices[l]
                answer = max(answer, profit)
            else:
                # 右指针价格更低，作为买入候选
                l = r

            # 右指针继续向右探索
            r += 1

        return answer