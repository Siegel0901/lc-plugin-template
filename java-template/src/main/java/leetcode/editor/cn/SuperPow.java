package leetcode.editor.cn;

public class SuperPow {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：快速幂
     * 1. 快速幂性质
     * 1.1. a^b = a*a^(b-1),b&1==1
     * 1.2. a^b = a^(b/2)*a(b/2),b&1==0
     * 1.3. (a*b)%m = (a%m)*(b%m)
     * 2. 如何处理指数数组 b = [b₀, b₁, ..., bₙ] ？
     * 2.1. a^b = a^(b₀×10ⁿ + b₁×10ⁿ⁻¹ + ... + bₙ)
     * = a^(b₀×10ⁿ) × a^(b₁×10ⁿ⁻¹) × ... × a^bₙ
     * 2.2. 设 f(a, b) = a^b mod m
     * 如果 b = [b₀, b₁, ..., bₙ]，可以分解为：
     * - 最后一位：bₙ
     * - 剩余部分：[b₀, b₁, ..., bₙ₋₁]
     * 那么：a^b = a^([b₀...bₙ₋₁]×10 + bₙ)
     * = (a^[b₀...bₙ₋₁])^10 × a^bₙ
     * 即：f(a, [b₀...bₙ]) = f(f(a, [b₀...bₙ₋₁]), 10) × f(a, bₙ) mod m
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    class Solution {
        int base = 1337;

        public int superPow(int a, int[] b) {
            return foo(a, b, b.length);
        }

        // a^b = a^([b₀...bₙ₋₁]×10 + bₙ) = (a^[b₀...bₙ₋₁])^10 × a^bₙ
        int foo(int a, int[] b, int len) {
            if (len == 0)
                return 1;
            // a^[b₀...bₙ₋₁])^10 % m
            int part1 = (int) binaryPow(foo(a, b, len - 1), 10, base);
            // a^bₙ % m
            int part2 = (int) binaryPow(a, b[len - 1], base);
            return part1 * part2 % base;
        }

        // 快速幂：计算(a^b)%m
        long binaryPow(int a, int b, int m) {
            if (b == 0)
                return 1;
            a %= m;
            if ((b & 1) == 1)
                return a * binaryPow(a, b - 1, m) % m;
            long mul = binaryPow(a, b / 2, m);
            return mul * mul % m;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SuperPow().new Solution();
        // put your test code here

    }
}