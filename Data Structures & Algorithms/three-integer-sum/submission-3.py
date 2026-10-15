class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        answer=[]
        nums.sort()
        n=len(nums)
        for i in range(n-2):
            # 相同的第一个数字，只处理一次
            # 当前这个值，之前已经当过第一个数字了，所以不用再当一次。
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            if nums[i]>0:
                break
            l=i+1
            r=n-1
            while(l<r):
                if nums[l]+nums[r]+nums[i]==0:
                    answer.append([nums[l], nums[r], nums[i]])
                    r-=1
                    l+=1
                    # 跳过重复的第二个数字
                    # [-2, 0, 0, 2, 2] i固定 -2
                    while l < r and nums[l] == nums[l - 1]:
                        l += 1

                    # 跳过重复的第三个数字
                    while l < r and nums[r] == nums[r + 1]:
                        r -= 1

                elif nums[l]+nums[r]+nums[i]>0:
                    r-=1
                elif nums[l]+nums[r]+nums[i]<0:
                    l+=1
        return answer