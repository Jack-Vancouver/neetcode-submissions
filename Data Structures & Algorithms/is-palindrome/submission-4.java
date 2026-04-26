class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z0-9]", "");

        char[] a = s.toCharArray();
        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            if (a[i] != a[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
