class Solution {
    public int[] twoSum(int[] nums, int target) {
        int a = 0; int b = 0;

        for(int i = 0; i < nums.length-1; i++){
            for(int j = 0; j < nums.length; j++){
                if(i == j){
                    j = j + 1;
                }
                if(nums[i]+nums[j] == target){
                    a = i;
                    b = j;
                }
            }
        }

        if(a<b){
            return new int[]{a, b}; 
        }
        else{
            return new int[]{b, a}; 
        }
        
    }
}
