import re
class Solution:
    def isPalindrome(self, s: str) -> bool:
        regularexpression=r'[^A-Za-z0-9]'
        s=re.sub(regularexpression,'',s)
        s=s.lower()
        l=0
        r=len(s)-1
        while(l<r):
            if(s[l]!=s[r]):
                return False
            l=l+1
            r=r-1
            continue
        return True