public class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) return false;

        for (int p = 2; p <= 5 && n > 0; p++) {
            while (n % p == 0) {
                n /= p;
            }
        }

        return n == 1;
    }
}