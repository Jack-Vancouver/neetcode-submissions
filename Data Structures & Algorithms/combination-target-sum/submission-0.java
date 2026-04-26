class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 【高级操作：提前排序】为了后面的“剪枝”做准备
        Arrays.sort(candidates);
        
        // 开启回溯 (结果集, 当前盘子, 菜单, 还差多少钱, 菜单翻到了第几页)
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> path, int[] candidates, int remain, int startIndex) {
        // 1. Base Case (终止条件)：刚好凑齐了！
        if (remain == 0) {
            // 注意！必须 new 一个新的 ArrayList，因为 path 一直在动态变化
            // 如果直接存 path，最后所有结果都会变成空盘子
            result.add(new ArrayList<>(path));
            return;
        }

        // 2. 遍历做选择
        // 从 startIndex 开始，这是为了避免重复组合 (比如有了 [2,3]，就不能再往回找 [3,2])
        for (int i = startIndex; i < candidates.length; i++) {
            
            // 【神级剪枝】：如果当前这道菜已经让我“买爆了” (remain - 菜价 < 0)
            // 因为我们前面排过序，后面的菜肯定更贵！直接 break 掉整个循环，后面的都不用试了！
            if (remain - candidates[i] < 0) {
                break;
            }

            // 做选择：把菜放进盘子
            path.add(candidates[i]);

            // 递归进入下一层
            // 【核心细节】：传入的依然是 i，而不是 i + 1！因为这道菜可以被无限次重复选！
            backtrack(result, path, candidates, remain - candidates[i], i);

            // 【灵魂操作：撤销选择 (回溯)】：从下一层退回来后，把刚才放进去的最后一道菜拿出来，准备尝试下一种
            path.remove(path.size() - 1);
        }
    }
}