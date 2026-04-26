public class Solution {
    public int maxArea(int[] heights) {
        // 水的面积 = 宽度 X 较矮的板
        // 1. 初始化左右双指针
        // 左指针 l 站在最左边，右指针 r 站在最右边
        // 这样一上来，我们就拥有了“最大的宽度”
        int l = 0;
        int r = heights.length - 1;
        
        // 用来记录我们一路走来遇到的“最大装水量”
        int res = 0;

        // 2. 双指针开始向中间靠拢，直到相遇为止
        while (l < r) {
            // 计算当前左右两块木板能装多少水
            // 宽度：(r - l)
            // 高度：受限于较矮的那块木板，也就是 Math.min(heights[l], heights[r])
            int area = Math.min(heights[l], heights[r]) * (r - l);
            
            // 更新历史最大装水量。如果当前这个组合比之前的大，就替换掉
            res = Math.max(res, area);
            
            // 3. 【核心灵魂】：淘汰短板！
            // 面积是由“短板”决定的。为了寻找可能更大的面积，我们必须把短板扔掉，去里面寻找更高的板。
            if (heights[l] <= heights[r]) {
                // 如果左边是短板，左指针往右走，寻找更高的板
                l++;
            } else {
                // 如果右边是短板，右指针往左走，寻找更高的板
                r--;
            }
        }
        
        // 循环结束，走遍了所有有希望的组合，返回最大值
        return res;
    }
}