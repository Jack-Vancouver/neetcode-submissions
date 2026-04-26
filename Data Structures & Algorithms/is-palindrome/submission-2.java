class Solution {
    public boolean isPalindrome(String s) {
        s=s.toLowerCase();
        s=s.replace(" ", "");
        s = s.replaceAll("[\\pP''“”]", "");
        int length = s.length();
        char a[]=s.toCharArray();
        int an = 0;
        
        int i = 0;
        int j = length - 1;
        while(i < length/2 || j > length/2){
            if(Character.compare(a[i], a[j])==0){
                an = an +0;
            }else{
                an = an +1;
            }
            i++;j--;
        }
        if(an == 0){
            return true;
        }else{
            return false;
        }
    }
}
