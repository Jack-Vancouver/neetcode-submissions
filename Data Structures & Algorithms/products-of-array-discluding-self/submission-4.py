class Solution:
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        n=len(nums)
        a=[1]*n
        temp =1
        for i in range(n):
            a[i]=temp
            temp*=nums[i]
        temp=1
        for i in range (n-1,-1,-1):
            a[i]*=temp
            temp*=nums[i]
        return a