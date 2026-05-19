class Solution {
    public int countSubstrings(String s) {
        // 极端情况防御
        if (s == null || s.length() == 0) {
            return 0;
        }

        int totalCount = 0;

        // 遍历所有的中心点
        for (int i = 0; i < s.length(); i++) {
            // 1. 以当前字符为中心（奇数长度回文串），向两边扩展并计数
            totalCount += countPalindromesAroundCenter(s, i, i);
            
            // 2. 以当前字符和下一个字符的缝隙为中心（偶数长度回文串），向两边扩展并计数
            totalCount += countPalindromesAroundCenter(s, i, i + 1);
        }

        return totalCount;
    }

    // --- 核心辅助函数：从给定的左右指针出发，向外扩展并清点合法回文串的数量 ---
    private int countPalindromesAroundCenter(String s, int left, int right) {
        int count = 0;
        
        // 只要没有越界，并且左右两边的字符相等，就一直往外扩
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // 匹配成功！说明找到了一个回文串，计数器加 1
            count++;
            
            // 指针向两边继续试探
            left--;
            right++;
        }
        
        // 返回在这个中心点一共找到了多少个回文串
        return count;
    }
}

// 这会不会重复计算（数重了）？

// 答案是绝对不会！因为每个回文串都有唯一的一个物理中心。
// 以 "aba" 为例：

// 当中心在首字母 'a' 时，它只能向外扩一次（找到 "a"）。

// 当中心在 'b' 时，它先找到中心的 "b"，然后往外扩找到 "aba"。

// 它们虽然都找到了回文串，但这些回文串的起点和终点坐标是绝对不一样的。
// 由于我们严格地遍历了物理上的每一个中心点，这就保证了既不遗漏，也绝不重复！

// 这是一种极其严谨的穷举（Brute Force）思想的优雅化。
// 这也是为什么在复杂的分布式系统设计里，我们总是倾向于找到那个“唯一确定的维度（比如中心点）”来做分片（Sharding）或者遍历，以保证数据不重不漏。