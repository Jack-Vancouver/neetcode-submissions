class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        l=0
        r=0
        a=set()
        count=0
        while(r<len(s)):
            while(s[r] in a):
                a.remove(s[l])
                l+=1
            a.add(s[r])
            count=max(count,len(a))
            r+=1
        return count