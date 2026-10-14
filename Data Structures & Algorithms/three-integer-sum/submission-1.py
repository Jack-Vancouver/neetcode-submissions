class Solution:
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        nums.sort()
        result = []
        n = len(nums)

        for i in range(n - 2):
            # 相同的第一个数字，只处理一次
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            # 最小的数字已经大于 0，不可能再凑出 0
            if nums[i] > 0:
                break

            left = i + 1
            right = n - 1

            while left < right:
                total = nums[i] + nums[left] + nums[right]

                if total < 0:
                    left += 1

                elif total > 0:
                    right -= 1

                else:
                    result.append(
                        [nums[i], nums[left], nums[right]]
                    )

                    left += 1
                    right -= 1

                    # 跳过重复的第二个数字
                    while left < right and nums[left] == nums[left - 1]:
                        left += 1

                    # 跳过重复的第三个数字
                    while left < right and nums[right] == nums[right + 1]:
                        right -= 1

        return result