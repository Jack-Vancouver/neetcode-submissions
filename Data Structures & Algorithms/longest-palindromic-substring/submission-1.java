class Solution {
    public String longestPalindrome(String s) {
        // 极端情况防御
        if (s == null || s.length() < 1) {
            return "";
        }
        
        // 记录最长回文子串的起始和结束位置
        int start = 0;
        int end = 0;
        
        // 把每一个字母都当做一次中心点去尝试
        for (int i = 0; i < s.length(); i++) {

            // 🚨 致命陷阱（单双数之分）：
            // 回文串有两种截然不同的中心结构：
            // 1.奇数长度（如 "aba"）：中心是一个字母 'b'。左右指针从同一个位置出发（left = i, right = i）。
            // 2.偶数长度（如 "abba"）：中心是两个字母之间的缝隙。左右指针从相邻的两个位置出发（left = i, right = i + 1）。
            // 所以，在一个长度为 N 的字符串里，真正可以作为“中心”的位置有 2N - 1 个（N 个字母加上 N - 1 个缝隙）。
            // 我们只需要把这 2N - 1 个中心点全部遍历一次，找出扩散范围最大的那个，问题就完美解决了！

            // 尝试 1：以单个字母为中心（奇数长度），比如 "aba"
            int len1 = expandAroundCenter(s, i, i);
            
            // 尝试 2：以两个字母的缝隙为中心（偶数长度），比如 "abba"
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // 取两种尝试中，更长的那一个
            int len = Math.max(len1, len2);
            
            // 如果这次找到的长度，比我们之前记录的全局最长还要长，就更新全局记录
            if (len > end - start) {
                // 【核心下标数学题】：根据中心点和长度，反推它的起点和终点
                start = i - (len - 1) / 2;
                end = i + len / 2;
                // 不要死记硬背，我们在脑子里推演一下：
                // 假设当前我们循环到了字母索引 i。

                // 如果是奇数回文 "aba"：i 在 'b' 上。总长度 len = 3。
                // start = i - (3 - 1) / 2 = i - 1 （完美定位到 'a'）
                // end = i + 3 / 2 = i + 1 （完美定位到右边的 'a'）

                // 如果是偶数回文 "abba"：i 在左边的 'b' 上，i + 1 在右边的 'b' 上。总长度 len = 4。
                // start = i - (4 - 1) / 2 = i - 1 （完美定位到左边的 'a'）
                // end = i + 4 / 2 = i + 2 （完美定位到右边的 'a'）

                // 这个公式利用了整型除法向下取整的特性，一套公式完美吃透了奇偶两种情况。
            }
        }
        
        // 截取并返回最终结果 (注意 substring 是左闭右开区间，所以 end + 1)
        return s.substring(start, end + 1);
    }
    
    // --- 核心辅助函数：从给定的左右指针开始，向两边扩散，返回能扩散的最大长度 ---
    private int expandAroundCenter(String s, int left, int right) {
        // 只要不越界，且左右两边的字符相等，就一直扩散
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;  // 左指针左移
            right++; // 右指针右移
        }
        
        // 循环被打破时，说明当前的 left 和 right 已经是不满足条件的了。
        // 真实的回文串边界是 left + 1 到 right - 1。
        // 它的长度是 (right - 1) - (left + 1) + 1 = right - left - 1。
        return right - left - 1; 
    }
}