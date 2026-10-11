class Solution:
    def longestConsecutive(self, nums: list[int]) -> int:
        num_set = set(nums)
        longest = 0

        for num in num_set:
            # 前一个数字存在，说明当前数字不是起点
            if num - 1 in num_set:
                continue

            current = num
            length = 1

            # 不断寻找下一个连续数字
            while current + 1 in num_set:
                current += 1
                length += 1

            longest = max(longest, length)

        return longest