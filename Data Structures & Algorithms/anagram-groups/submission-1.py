class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        groups = {}

        for word in strs:
            count = [0]*26
            for char in word:
                index = ord(char)-ord('a')
                count[index] += 1

            key = tuple(count) #把count转换成元组，复制给key

            if key not in groups:
                groups[key] = [] #group 字典，把key作为键，然后值是空数组，做出初步形状【[],[],[]】

            groups[key].append(word)#把word加进去当前的key里。
        return list(groups.values())