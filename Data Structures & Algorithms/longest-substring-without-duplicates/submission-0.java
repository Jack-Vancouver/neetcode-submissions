class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 准备一个 HashSet，它就是我们的“窗口”。用来记录当前窗口里有哪些字母。
        Set<Character> window = new HashSet<>();
        
        int left = 0;      // 窗口的左边界
        int maxLength = 0; // 历史最长记录

        // 右边界 (right) 开始从头到尾扫视整个字符串
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right); // 当前右边界准备吞进来的新字母

            // 【核心逻辑】：如果新字母已经在窗口里了，触发警报！
            // 左边界开始往右移，一路走一路把字母从窗口里扔出去，直到不重复为止。
            while (window.contains(rightChar)) {
                char leftChar = s.charAt(left);
                window.remove(leftChar); // 把左边缘的字母踢出窗口
                left++;                  // 左边界往右缩进一格
            }

            // 警报解除（或者本来就没警报），安全地把新字母放进窗口
            window.add(rightChar);

            // 算一下现在窗口有多宽 (right - left + 1)，如果破记录了就更新
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}