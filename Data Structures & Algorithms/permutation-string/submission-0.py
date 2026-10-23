class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        m = len(s1)

        if m > len(s2):
            return False

        target = {}
        window = {}

        # 统计 s1 的字符次数
        for char in s1:
            target[char] = target.get(char, 0) + 1

        l = 0

        for r in range(len(s2)):
            # 加入右端字符
            char = s2[r]
            window[char] = window.get(char, 0) + 1

            # 窗口过长，移除左端字符
            if r - l + 1 > m:
                old = s2[l]
                window[old] -= 1

                # 次数为 0，删除这条记录
                if window[old] == 0:
                    del window[old]

                l += 1

            # 当前窗口与 s1 的字符次数完全一致
            if r - l + 1 == m and window == target:
                return True

        return False