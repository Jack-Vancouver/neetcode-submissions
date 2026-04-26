class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        // 如果只有 1 个元素，或者根本没旋转（第一个小于最后一个），直接返回第一个
        if (nums[l] <= nums[r]) {
            return nums[l];
        }

        // 开始二分查找
        while (l < r) {
            // 找中间位置 (这是防止整型溢出的标准写法，等同于 (l + r) / 2)
            int mid = l + (r - l) / 2;

            if (nums[mid] > nums[r]) {
                // 中间比右边大，说明悬崖在右边。
                // 既然 nums[mid] 已经比别人大了，它肯定不是最小值，所以 l = mid + 1
                l = mid + 1;
            } else {
                // 中间比右边小，说明右半段是正常的。
                // 最小值在左半段，或者就是 mid 本身。所以保留 mid！
                r = mid;
            }
        }
        
        // 当 l 和 r 相遇时，指着的就是那个唯一的悬崖底（最小值）
        return nums[l];
    }
}