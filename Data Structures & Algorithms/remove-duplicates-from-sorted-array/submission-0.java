class Solution {
    public int removeDuplicates(int[] nums) {
        int down = 0;
        for(int up = 1;up<nums.length;up++){
            if(nums[down]!=nums[up]){
                down++;
                nums[down]=nums[up];
            }
        }
        return down+1;
    }
}
