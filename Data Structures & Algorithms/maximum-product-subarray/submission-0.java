class Solution {
    public int maxProduct(int[] nums) {
        // 极端情况防御
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 初始化：刚站在第 0 个数字时，最大值、最小值、全局结果都是它自己
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];

        // 从第 1 个数字开始往下走
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            // 【核心微操】：因为接下来的计算会覆盖 maxSoFar，
            // 但计算 minSoFar 时还需要用到老的 maxSoFar，所以必须先把它存到临时变量里！
            int tempMax = maxSoFar;

            // 状态转移：计算当前位置的最大值
            // 在 (自己单干, 老大乘自己, 老幺乘自己) 三者中选一个最大的
            maxSoFar = Math.max(curr, Math.max(tempMax * curr, minSoFar * curr));

            // 状态转移：计算当前位置的最小值（储备底牌）
            // 在 (自己单干, 老大乘自己, 老幺乘自己) 三者中选一个最小的（最负的）
            minSoFar = Math.min(curr, Math.min(tempMax * curr, minSoFar * curr));

            // 每次更新完，立刻和全局的历史最高纪录比对一下
            result = Math.max(result, maxSoFar);
        }

        return result;
    }
}