public class Solution {
    public String minWindow(String s, String t) {
        // 1. 极端情况防御
        if (t.isEmpty()) return "";

        // 2. 准备两个记事本 (HashMap)
        // countT: 客人的点菜单（需要什么食材，各多少份）
        Map<Character, Integer> countT = new HashMap<>();
        // window: 我们的备菜篮子（当前窗口里有什么食材，各多少份）
        Map<Character, Integer> window = new HashMap<>();
        
        // 把客人的点菜单录入 countT
        for (char c : t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        // 3. 核心状态标志
        int have = 0; 
        int need = countT.size(); // 需要满足的【食材种类】数量
        
        // 用来记录历史最短窗口的信息：res[0] 是左边界，res[1] 是右边界
        int[] res = {-1, -1};
        // 历史最短窗口的长度，初始设为无限大，方便后面被不断替换得更小
        int resLen = Integer.MAX_VALUE; 
        
        int l = 0; // 窗口左边界

        // 4. 右边界 r 开始在传送带上拉框
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r); // 拿到传送带滚过来的新食材
            // 放进我们的备菜篮子
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 【关键判定 1】：新来的食材是不是客人点的？
            // 并且，加上这个食材后，篮子里的份数是不是【刚好】满足了客人的要求？
            // 如果刚好满足，我们手里的达标种类 (have) 就增加 1！
            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))) {
                have++;
            }

            // 5. 警报触发：凑齐了！(have == need)
            // 说明当前篮子里的东西绝对够出餐了，接下来我们要尝试“精简”篮子（缩小窗口）
            while (have == need) {
                
                // (1) 先看看当前的篮子是不是比历史记录更小？是的话赶紧记下来！
                if ((r - l + 1) < resLen) {
                    resLen = r - l + 1;
                    res[0] = l;
                    res[1] = r;
                }

                // (2) 准备精简：把篮子最左边的食材扔出去
                char leftChar = s.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1); // 篮子里减去 1 份
                
                // 【关键判定 2】：扔掉这个食材，会不会导致我们“不够出餐”了？
                // 如果扔掉的是客人点的核心食材，而且扔完后，篮子里的份数【小于】客人的要求了
                if (countT.containsKey(leftChar) && window.get(leftChar) < countT.get(leftChar)) {
                    // 完了，有一道菜不达标了，达标种类 (have) 减少 1
                    have--; 
                }
                
                // 左边界向右收缩
                l++;
            }
        }

        // 6. 循环结束，检查有没有找到过合适的窗口
        // 如果 resLen 还是无限大，说明根本没凑齐过，返回空字符串
        // 否则，根据记录的左右边界，把这段字符串截取出来交差！
        return resLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
    }
}