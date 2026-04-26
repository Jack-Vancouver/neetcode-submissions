class Solution {
    public int longestConsecutive(int[] nums) {
        // 边界情况处理
        if (nums == null || nums.length == 0) return 0;

        // 1. 把所有数字扔进 HashSet 去重且实现 O(1) 查找
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        // 2. 遍历 Set 中的每一个数字
        for (int num : set) {
            // 【核心魔术】：只有当 num-1 不存在时，我们才认为 num 是一个序列的开头
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;

                // 3. 顺藤摸瓜，找 x+1, x+2...
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                // 4. 更新最长记录
                longest = Math.max(longest, currentLength);

                // 【你的专属优化】：如果当前长度已经超过数组一半，直接下班！
                if (longest > nums.length / 2) {
                    return longest;
                }
            }
        }

        return longest;
    }
}