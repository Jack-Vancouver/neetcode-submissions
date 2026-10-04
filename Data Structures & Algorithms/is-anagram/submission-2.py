from collections import Counter

class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        return Counter(s) == Counter(t)
        # Counter 会自动统计每个字符出现的次数：
        # Counter("aab")
        # Counter({'a': 2, 'b': 1})