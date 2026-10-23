class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        l = 0
        r = 0
        counts = {}
        answer = 0

        while r < len(s):
            # 加入右端字符，更新出现次数
            counts[s[r]] = counts.get(s[r], 0) + 1

            # 需要替换的字符太多，就收缩左边界
            while r - l + 1 - max(counts.values()) > k:
                counts[s[l]] -= 1
                l += 1

            # 当前窗口合法，记录最大长度
            answer = max(answer, r - l + 1)

            r += 1

        return answer