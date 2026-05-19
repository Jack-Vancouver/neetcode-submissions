class Solution {
    public int numDecodings(String s) {
        // 0. 极端情况与地雷防御：如果密文为空，或者开头就是 '0'，直接死刑
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // prev2 代表走两步之前的翻译方法数 (相当于 dp[i-2])
        // 为什么设为 1？因为如果遇到合法的两位数（比如开头是 "22"），
        // 那么把 "22" 整体当作一步切下来的走法是 1 种。这是基础底座。
        int prev2 = 1; 
        
        // prev1 代表走一步之前的翻译方法数 (相当于 dp[i-1])
        // 因为 s.charAt(0) != '0' 已经验证过了，所以第一个字符的合法切法是 1 种。
        int prev1 = 1; 

        // current 代表当前处理到第 i 个字符时的总翻译方法数
        int current = 0;

        // 从密文的第 2 个字符（索引 1）开始挨个破译
        for (int i = 1; i < s.length(); i++) {
            current = 0; // 每一轮开始前先清零

            // 选择 A：只切当前 1 个字符
            // 只要当前字符不是 '0'，这条路就通，继承 prev1
            if (s.charAt(i) != '0') {
                current += prev1;
            }

            // 选择 B：把当前字符和前一个字符打包切 2 个
            // 提取出这两个字符组成的两位数
            int twoDigit = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            // 只要这个两位数在 10 到 26 之间，这条路就通，继承 prev2
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prev2;
            }

            // 【灵魂操作：毛毛虫向前滚动】
            prev2 = prev1;
            prev1 = current;
        }

        return prev1; // 走到最后，prev1 就是最终的总方法数
    }
}