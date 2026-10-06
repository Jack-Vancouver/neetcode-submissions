class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        #1.字典使用哈希来快速定位键。
        #键需要支持稳定的哈希和相等比较，因此 Python 不允许普通可变列表充当键。转换元组后，就满足要求.字符串也可以。

        #2.为什么 count 放在外层循环里面、内层循环外面？
        #因为：每个单词需要一份新的计数表，但同一个单词的所有字符需要共用这份计数表。

        #3.最外面的 [] 是什么时候建立的？
        #是在最后的 list(groups.values()) 中建立的。
        groups = {}
        for word in strs:
            count = [0] * 26
            for char in word:
                index = ord(char) - ord('a')
                count[index]+=1
            key = tuple(count)
            if key not in groups:
                groups[key]=[]

            groups[key].append(word)
        return list(groups.values())