import re
class Solution:
    def isPalindrome(self, s: str) -> bool:
        # isalnum() method returns True 
        # if all characters in a string are alphanumeric (字母或数字) 
        # and the string is at least one character long (不是空的)
        left = 0
        right = len(s) - 1

        while left < right:
            # 左边不是字母或数字，跳过
            if not s[left].isalnum():
                left += 1
                continue

            # 右边不是字母或数字，跳过
            if not s[right].isalnum():
                right -= 1
                continue

            # 两边都是有效字符，忽略大小写进行比较
            if s[left].lower() != s[right].lower():
                return False

            left += 1
            right -= 1

        return True