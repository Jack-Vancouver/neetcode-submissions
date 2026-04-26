class Solution {
    public boolean isUgly(int n) {
        if (n ==1) return true;
        else if (n<=0) return false;
        else if (n%2 == 0||n%3 == 0||n%5 == 0){
            while(n>1){
                if (n%2 == 0) n=n/2;
                else if (n%3 == 0) n=n/3;
                else if (n%5 == 0) n=n/5;
                else return false;
            }
            return true;
        }
        else{return false;}
    }
}