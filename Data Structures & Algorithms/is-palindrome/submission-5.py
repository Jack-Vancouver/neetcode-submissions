import re
class Solution:
    def isPalindrome(self, s: str) -> bool:
        s=s.lower()
        s=re.sub(r'[^a-z0-9]', '', s)
        l = len(s)
        for i in range(l//2):
            if s[i]==s[l-1-i]:
                continue
            if s[i]!=s[l-1-i]:
                return False
        return True