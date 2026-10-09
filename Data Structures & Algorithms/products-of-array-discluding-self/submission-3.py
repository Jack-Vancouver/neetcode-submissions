class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)

        a=[1]*n
        for i in range(1,n,1):
            
            # a[0]=left
            # a[1]=nums[0]
            # a[2]=nums[1]*nums[0]
            # a[3]=nums[2]*nums[1]*nums[0]
            # a[4]=nums[3]*nums[2]*nums[1]*nums[0]

            # a[0]=left
            # a[1]=nums[0]*a[0]
            # a[2]=nums[1]*a[1]
            # a[3]=nums[2]*a[2]
            # a[4]=nums[3]*a[3]

            a[i]=nums[i-1]*a[i-1]
            
        b=[1]*n
        for i in range(n-1,0,-1):

            # a[4]=right
            # a[3]=nums[4]
            # a[2]=nums[4]*nums[3]
            # a[1]=nums[4]*nums[3]*nums[2]
            # a[0]=nums[4]*nums[3]*nums[2]*nums[1]*nums[0]

            # a[4]=right
            # a[3]=nums[4]*a[4]
            # a[2]=nums[3]*a[3]
            # a[1]=nums[2]*a[2]
            # a[0]=nums[1]*a[1]

            b[i-1]=nums[i]*b[i]

        for i in range (n):
            a[i]=a[i]*b[i]

        return a