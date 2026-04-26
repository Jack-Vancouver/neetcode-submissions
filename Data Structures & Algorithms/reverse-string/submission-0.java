class Solution {
    public void reverseString(char[] s) {
        // 1. 如果是 0 或 1 个字符，直接返回（虽然 LeetCode 保证 s 不为空，但这是好习惯）
        if (s == null || s.length <= 1) return;

        // 2. 初始化左右指针
        int left = 0;
        int right = s.length - 1;

        // 3. 交换并向中间靠拢
        while (left < right) {
            // 经典的交换三步走
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // 移动指针
            left++;
            right--;
        }
    }
}