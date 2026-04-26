class Solution {
    public boolean isPowerOfTwo(int n) {
        int i=0;
        int odd=0;
        if (n==1){
            return true;
        }if (n==3){
            return false;
        }if (n%2==1){
            return false;
        }
        else{
            while(n>1){
                n = n/2;
                odd = n%2;
                if (odd == 1){
                    i=i+1;
                }
            }
            
        }
        if (i == 1){
            return true;
        }
        else{
            return false;
        }
    }
}