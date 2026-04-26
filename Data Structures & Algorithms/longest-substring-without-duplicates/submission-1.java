public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 记事本：记录【字母】以及它【最后一次出现的位置下标】
        HashMap<Character, Integer> mp = new HashMap<>();
        
        int l = 0;   // 窗口的左边界
        int res = 0; // 历史最长记录

        // 右边界 r 开始往前走
        for (int r = 0; r < s.length(); r++) {
            
            // 🚨 警报触发：如果我们发现这个字母之前见过！
            if (mp.containsKey(s.charAt(r))) {
                // 【核心瞬移魔法】：
                // 既然当前字母重复了，左边界 l 就直接跳到【这个字母上次出现位置的下一个位置】。
                // 为什么要加个 Math.max？这是为了防止左边界“开倒车”（后面详细讲）。
                l = Math.max(mp.get(s.charAt(r)) + 1, l);
            }
            
            // 警报解除（或者原本就没重复）。
            // 更新记事本：无论如何，把当前字母和它最新的位置 r 记下来。
            // 如果是老字母，这步会覆盖掉它以前的旧位置。
            mp.put(s.charAt(r), r);
            
            // 算一下当前窗口的长度，如果破记录了就更新
            res = Math.max(res, r - l + 1);
        }
        
        return res;
    }
}