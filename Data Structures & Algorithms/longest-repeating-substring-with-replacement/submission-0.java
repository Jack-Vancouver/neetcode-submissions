class Solution {
    public int characterReplacement(String s, int k) {
        // 记事本：记录当前窗口内每个字母出现的次数 (A 对应 0，B 对应 1...)
        int[] count = new int[26];
        
        int left = 0;       // 窗口左边界
        int maxFreq = 0;    // 窗口内出现次数最多的字母的频次
        int maxLength = 0;  // 历史最长记录

        // 右边界开始拉框
        for (int right = 0; right < s.length(); right++) {
            // 1. 新字母进窗口，记事本上对应的次数 +1
            char rightChar = s.charAt(right);
            count[rightChar - 'A']++;
            
            // 2. 更新“老大”的势力（频次）
            // 看看新进来的字母，能不能挑战历史最高频次
            maxFreq = Math.max(maxFreq, count[rightChar - 'A']);

            // 3. 【核心判断】：算一算卧底的数量是不是超标了？
            // 卧底数量 = 当前窗口长度 (right - left + 1) - 老大频次 (maxFreq)
            while ((right - left + 1) - maxFreq > k) {
                // 警报触发！作弊卡不够用了！左边界必须往右缩
                char leftChar = s.charAt(left);
                // 把被踢出去的字母，在记事本上 -1
                count[leftChar - 'A']--;
                // 左边界往右走
                left++;
            }

            // 4. 警报解除（或者原本就没超标），记录当前窗口长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}