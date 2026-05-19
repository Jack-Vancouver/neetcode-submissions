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