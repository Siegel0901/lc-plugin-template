package leetcode.editor.cn;

public class UglyNumber {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 正整数唯一分解定理：任意一个大于1的自然数，要么它本身是质数，要么它可以分解为若干质数的乘积
     */
    class Solution {
        public boolean isUgly(int n) {
            if (n <= 0)
                return false;
            while (n % 2 == 0) n /= 2;
            while (n % 3 == 0) n /= 3;
            while (n % 5 == 0) n /= 5;
            return n == 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new UglyNumber().new Solution();
        // put your test code here

    }
}