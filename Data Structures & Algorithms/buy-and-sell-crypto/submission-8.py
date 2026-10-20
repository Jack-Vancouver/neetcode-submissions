class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        a = 0
        l=0
        r=1
        while(r<len(prices)):
            a=max(a,prices[r]-prices[l])
            if(prices[l]>prices[r]):
                l=r
                r+=1
            else:
                r+=1
        return a