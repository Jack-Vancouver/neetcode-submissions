class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        // 因为是找特定目标，必须用 <= 保证所有抽屉都被打开看过
        while (l <= r) {
            int mid = l + (r - l) / 2;

            // 运气爆棚，直接一刀切中目标！
            if (nums[mid] == target) {
                return mid;
            }

            // 【核心逻辑】：判断哪一半是“安全区”
            if (nums[l] <= nums[mid]) { 
                // 左半段是完美的升序（安全区）
                
                // 既然左边是安全的，我们判断 target 在不在这个完美区间内？
                if (target >= nums[l] && target < nums[mid]) {
                    // target 就在这里面！抛弃右半段
                    r = mid - 1;
                } else {
                    // target 不在这个完美区间，那只能去右半段的乱象里找了
                    l = mid + 1;
                }
            } else { 
                // 右半段是完美的升序（安全区）
                
                // 既然右边是安全的，我们判断 target 在不在这个完美区间内？
                if (target > nums[mid] && target <= nums[r]) {
                    // target 就在这里面！抛弃左半段
                    l = mid + 1;
                } else {
                    // target 不在这个完美区间，只能去左半段找了
                    r = mid - 1;
                }
            }
        }

        // 找遍了所有的可能性都没碰到，返回 -1
        return -1;
    }
}