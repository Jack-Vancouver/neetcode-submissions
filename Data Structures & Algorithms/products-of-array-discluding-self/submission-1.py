class Solution:
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        n = len(nums)
        answer = [1] * n

        # 第一遍：记录每个位置左边所有元素的乘积
        left = 1

        for i in range(n):
            answer[i] = left
            left *= nums[i]

        # 第二遍：乘上每个位置右边所有元素的乘积
        right = 1

        for i in range(n - 1, -1, -1):
            answer[i] *= right
            right *= nums[i]

        return answer