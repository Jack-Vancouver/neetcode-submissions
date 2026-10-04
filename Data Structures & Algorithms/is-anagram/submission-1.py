class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t):
            return False

        count = [0] * 26 # [0] 是包含一个零的列表，* 26 将列表内容重复 26 次：
        # [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

        for char in s:
            index = ord(char) - ord('a')
            # ord() 返回一个字符的 Unicode 编码整数：
            # ord('a')  # 97
            # ord('b')  # 98
            count[index] += 1
            # count[0]   # a 的计数
            # count[1]   # b 的计数

        for char in t:
            index = ord(char) - ord('a')
            count[index] -= 1
            # Python 没有 Java 的 ++、-- 自增和自减运算符，一般写 += 1、-= 1。

        for num in count:
            if num != 0:
                return False

        return True