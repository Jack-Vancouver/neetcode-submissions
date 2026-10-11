import re
#正则
class Solution:
    def isPalindrome(self, s: str) -> bool:
        s=s.lower()
        regularexpression_pattern=r'[^A-Za-z0-9]'
        # 匹配一个不是 a、b、c 的字符
        s=re.sub(regularexpression_pattern,"",s)
        #re.sub(匹配规则, 替换内容, 原字符串)
        l=0
        r=len(s)-1
        while l<r:
            if s[l]!=s[r]:
                return False
            l+=1
            r-=1
        return True