public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 第一步：准备一个“记事本”，用来记录每个数字出现的次数
        // Key（键）：数字本身
        // Value（值）：出现的频次
        Map<Integer, Integer> count = new HashMap<>();
        
        // 第二步：准备一排“桌子”（也就是桶 Bucket）
        // 数组的下标 (index) 代表“出现的频次”
        // 数组里面存的是一个 List，代表“出现过这么多次的数字有哪些”
        // 为什么长度是 nums.length + 1？
        // 因为如果数组有 5 个元素，一个数字最多出现 5 次，所以我们需要到 5 号桌，加上数组从0开始，一共六个。
        List<Integer>[] freq = new List[nums.length + 1];

        // 把每张桌子都初始化为空的列表，防止后面报 NullPointerException (空指针异常)
        // 初始化freq[0] freq[1] freq[2] ... 里面的列表
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // ==========================================
        // 阶段一：点单记账
        // ==========================================
        for (int n : nums) {
            // 这是 Java 统计频次的经典神仙写法！
            // getOrDefault(n, 0) 的意思是：
            // 记事本里如果有 n，就把它的次数拿出来；如果没有 n，说明是第一次见，次数当成 0。
            // 然后在原来次数的基础上 +1，存回记事本里。
            
            // getOrDefault() 是 Java Map 接口方法，用于检索给定key的值，或者在key不存在或映射为 null 时返回指定的默认值。
            // 它可以防止 NullPointerException 并消除显式 if (map.containsKey(...)) 检查的需要，使代码更加简洁。
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // ==========================================
        // 阶段二：安排入座（桶排序核心）
        // ==========================================
        // count.entrySet() 是把 HashMap 里的数据一对一对地拿出来
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            // entry.getValue() 是频次（也就是我们要找的“桌号”）
            // entry.getKey() 是数字本身
            // 这一句翻译过来就是：找到对应频次的桌子，把这个数字安排进去。
            freq[entry.getValue()].add(entry.getKey());
        }

        // ==========================================
        // 阶段三：查房选人（从大桌子往小桌子找）
        // ==========================================
        int[] res = new int[k]; // 准备用来装最终结果的数组
        int index = 0;          // 结果数组的当前填充位置

        // 从最大号的桌子（freq.length - 1）开始，倒着往前查房
        for (int i = freq.length - 1; i > 0 && index < k; i--) {
            // freq[i] 就是当前频次对应的 List 列表
            for (int n : freq[i]) {
                // 如果这张桌子上有人（有数字），就把他拉进我们的结果队伍里
                res[index++] = n;
                
                // 如果队伍已经满 K 个人了，立刻下班，返回结果！
                if (index == k) {
                    return res;
                }
            }
        }
        
        return res;
    }
}