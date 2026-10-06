class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        count = {}

        # 1. 统计每个数字的出现次数
        for num in nums:
            if num not in count:
                count[num] = 0

            count[num] += 1

        # 2. 创建桶，下标代表出现次数
        buckets = [[] for _ in range(len(nums) + 1)]

        # 3. 把数字放进对应次数的桶
        for num, frequency in count.items():
            buckets[frequency].append(num)

        # 4. 从次数最高的桶开始收集答案
        answer = []

        for frequency in range(len(nums), 0, -1):
            for num in buckets[frequency]:
                answer.append(num)

                if len(answer) == k:
                    return answer

        return answer

        # 解决了之前“次数作为字典键会覆盖”的问题
        # 
        # 如果多个数字次数相同
        # count = {5: 2, 8: 2}
        # buckets[2].append(5)
        # buckets[2].append(8)
        # 得到
        # buckets[2] = [5, 8]