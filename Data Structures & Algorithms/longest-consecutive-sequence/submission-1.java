public class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (!mp.containsKey(num)) {
                mp.put(num, mp.getOrDefault(num - 1, 0) + mp.getOrDefault(num + 1, 0) + 1);
                mp.put(num - mp.getOrDefault(num - 1, 0), mp.get(num));
                mp.put(num + mp.getOrDefault(num + 1, 0), mp.get(num));
                res = Math.max(res, mp.get(num));
            }
        }
        return res;
    }
}

// 动态演示 (模拟数组 [4, 1, 3, 2])
// 让我们一步步看看这个 HashMap 是怎么“合并孤岛”的。
// 初始状态：Map mp = {}, res = 0
// 
// 1. 进来数字 4
// 左看 (3)：没有，left = 0
// 右看 (5)：没有，right = 0
// 新长度：0 + 0 + 1 = 1
// 更新自己和边界（其实全是 4 自己）：mp.put(4, 1)
// 当前 Map：{4: 1}, res = 1
// 领地 [4] 建立，长度 1)
// 
// 2. 进来数字 1
// 左看 (0)：没有，left = 0
// 右看 (2)：没有，right = 0
// 新长度：0 + 0 + 1 = 1
// 更新边界：mp.put(1, 1)
// 当前 Map：{4: 1, 1: 1}, res = 1
// 领地 [1] 建立，长度 1)
// 
// 3. 进来数字 3 (开始产生连接！)
// 左看 (2)：没有，left = 0
// 右看 (4)：有！ 4 的记录是 1。right = 1
// 新长度：0 + 1 + 1 = 2
// 把 3 放进 Map 占坑：mp.put(3, 2)
// 更新左边界哨所 (3 - 0 = 3)：mp.put(3, 2)
// 更新右边界哨所 (3 + 1 = 4)：mp.put(4, 2)
// 当前 Map：{1: 1, 3: 2, 4: 2}, res = 2
// 领地 [3, 4] 合并成功，左右边界 3 和 4 都记录了总长度 2)
// 
// 4. 进来数字 2 (终极合并！)
// 左看 (1)：有！ left = 1
// 右看 (3)：有！ 找 3 拿到长度，right = 2
// 新长度：1 + 2 + 1 = 4
// 把 2 放进去占坑：mp.put(2, 4)
// 更新左边界哨所 (2 - 1 = 1)：mp.put(1, 4)
// 更新右边界哨所 (2 + 2 = 4)：mp.put(4, 4)
// 当前 Map：{1: 4, 2: 4, 3: 2, 4: 4}, res = 4
// 
// 你发现了吗？在最后一步，1 和 4 这两个边界都成功把记录更新成了 4。
// 中间的 3 的记录还是老的 2，但完全没关系，因为它被包在中间，以后再也不会有新的数字跟它接触了。