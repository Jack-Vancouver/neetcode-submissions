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

    // 🎬 第一层（初始状态）
    // •	当前状态：remain = 7，path = []，startIndex = 0。
    // •	开始遍历菜单：从 i = 0（菜品 2）开始。
    // 尝试选 2：
    // •	检查：7 - 2 = 5 >= 0，买得起。
    // •	做选择：把它放进盘子。path 变成 [2]。
    // •	递归：端着盘子进入下一层，remain 变成 5。因为可以重复选，所以下一层依然从 i = 0 开始。
    // 🎬 第二层（盘子里有 [2]）
    // •	当前状态：remain = 5，path = [2]，startIndex = 0。
    // •	开始遍历菜单：从 i = 0（菜品 2）开始。
    // 尝试再选 2：
    // •	检查：5 - 2 = 3 >= 0，买得起。
    // •	做选择：放进盘子。path 变成 [2, 2]。
    // •	递归：端着盘子进入下一层，remain 变成 3，startIndex = 0。
    // 🎬 第三层（盘子里有 [2, 2]）
    // •	当前状态：remain = 3，path = [2, 2]，startIndex = 0。
    // •	开始遍历菜单：从 i = 0（菜品 2）开始。
    // 尝试再再选 2：
    // •	检查：3 - 2 = 1 >= 0，买得起。
    // •	做选择：放进盘子。path 变成 [2, 2, 2]。
    // •	递归：端着盘子进入下一层，remain 变成 1，startIndex = 0。
    // 🎬 第四层（盘子里有 [2, 2, 2]） —— 💥 碰壁与神级剪枝
    // •	当前状态：remain = 1，path = [2, 2, 2]，startIndex = 0。
    // •	开始遍历菜单：从 i = 0（菜品 2）开始。
    // 尝试再再再选 2：
    // •	检查：1 - 2 = -1 < 0。超预算了！
    // •	【神级剪枝】：既然选 2 都超预算，因为菜单是排好序的，后面的 3、6、7 肯定更贵！直接 break，这层的循环直接结束！
    // •	本层结束，向上退回。
    // 🎬 回到第三层（执行灵魂回溯）
    // 计算机从第四层退回到了第三层，此时：
    // •	【撤销选择】：刚才放入的第三个 2 导致了碰壁，把它拿出来！
    // •	path.remove 后，path 恢复为 [2, 2]，remain 恢复为 3。
    // •	继续第三层的循环：刚才 i = 0（菜品 2）试过了，现在轮到 i = 1（菜品 3）。
    // 尝试选 3：
    // •	检查：3 - 3 = 0。
    // •	做选择：放进盘子。path 变成 [2, 2, 3]。
    // •	递归：进入下一层，remain 变成 0。
    // 🎬 第四层（盘子里有 [2, 2, 3]） —— 🎉 成功收集
    // •	当前状态：remain = 0。
    // •	触发 Base Case：刚好凑齐！
    // •	收集结果：把 [2, 2, 3] 抄到记事本 result 里。
    // •	本层结束，直接 return 退回。
    // 🎬 疯狂回溯的连环反应
    // 	1.	回到第三层：刚收集完成功结果，把 3 拿出来！path 恢复为 [2, 2]。
    // •	第三层继续试 i = 2（菜品 6），发现 3 - 6 < 0，剪枝 break。第三层彻底结束，退回第二层。
    // 	2.	回到第二层：把第二个 2 拿出来！path 恢复为 [2]，remain 恢复为 5。第二层继续试 i = 1（菜品 3）。
    // •	放 3 进去，path 变成 [2, 3]，remain 变成 2。
    // •	递归下去，发现剩下的钱买不起任何东西了。
    // •	（最小的菜是 2，但从 startIndex=1 开始，只能买 3 或更贵的，2 - 3 < 0，直接剪枝）。退回。
    // 	3.	退回第二层：把 3 拿出来，试 6，买不起剪枝。退回第一层。
    // 	4.	回到第一层：把最开始的那个 2 拿出来！此时 path = []，变成了空盘子。remain 恢复为 7。
    // •	第一层开始试 i = 1（菜品 3）。开始新一轮的探索……（重复上述进进退退的过程）。
    // 🎬 直到遍历到菜品 7
    // 在第一层，当循环走到 i = 3（菜品 7）时：
    // •	检查：7 - 7 = 0。
    // •	做选择：放入盘子，path 变成 [7]。
    // •	递归：进入下一层，remain = 0，直接收集结果！
    // •	回溯：拿出 7，path 变为空。第一层循环结束。
    // 最终结果：[[2, 2, 3], [7]]。