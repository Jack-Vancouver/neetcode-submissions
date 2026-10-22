class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        l = 0
        r = 0
        chars = set()
        count = 0

        while r < len(s):
            # 当前字符已经存在，就收缩左边界
            while s[r] in chars:
                chars.remove(s[l])
                l += 1

            # 重复已经消除，可以加入当前字符
            chars.add(s[r])

            # 当前窗口是从 l 到 r，两端都包含
            count = max(count, r - l + 1)

            r += 1

        return count