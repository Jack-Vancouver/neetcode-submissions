class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        count = [0] * 26
        for i in s:
            x = ord(i)-ord('a')
            count[x]+=1
        for i in t:
            x = ord(i)-ord('a')
            count[x]-=1

        return count == [0]*26