class Solution:
    def maxArea(self, height: list[int]) -> int:
        l = 0
        r = len(height) - 1
        answer = 0

        while l < r:
            # 当前容器的高度、宽度和面积
            water_height = min(height[l], height[r])
            width = r - l
            area = water_height * width

            # 保留目前找到的最大面积
            answer = max(answer, area)

            # 移动较矮的一边
            if height[l] < height[r]:
                l += 1
            else:
                r -= 1

        return answer