class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 1. 特判：如果数组为空，直接返回空列表
        if (strs == null || strs.length == 0) return new ArrayList<>();

        // 2. 创建一个大 Map
        // Key:   排序后的字符串 (特征码)
        // Value: 属于这个特征的所有原单词 (列表)
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // 3. 提取特征：将字符串转为数组 -> 排序 -> 变回字符串
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);

            // 4. 分组录入：
            // 如果 map 里还没这个 key，先建个空的 List
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // 把当前原单词 s 放进对应的 List 里
            map.get(key).add(s);
        }

        // 5. 最后把 Map 里所有的“值”（List）打包返回
        return new ArrayList<>(map.values());
    }
}




// 完整的生命周期演示
// 假设输入是 ["eat", "tea"]：

// 第一轮 (s 是 "eat"):
// 算出 key 是 "aet"。
// Map 里没这个 key，于是 map.put("aet", new ArrayList<>())。
// 执行 map.get("aet").add("eat")。
// 此时 Map 状态：{"aet": ["eat"]}

// 第二轮 (s 是 "tea"):
// 算出 key 也是 "aet"。
// Map 里已经有这个 key 了，跳过 if。
// 执行 map.get("aet").add("tea")。
// 此时 Map 状态：{"aet": ["eat", "tea"]}