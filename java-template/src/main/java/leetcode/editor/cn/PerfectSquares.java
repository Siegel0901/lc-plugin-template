package leetcode.editor.cn;

public class PerfectSquares {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：数学解法
     * 1. 四平方和定理：任何正整数都可以表示为至多四个整数的平方和。
     * 时间复杂度：O(sqrt(n))
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int numSquares(int n) {
            // 四平方和定理充要条件：当且仅当n去掉因子4后模8余7时，需要4个完全平方数加和。
            while (n % 4 == 0) n /= 4;
            if (n % 8 == 7) return 4;
            // 判断n是否为完全平方数：Math.sqrt(n) == (int) Math.sqrt(n)，只需1个完全平方数n
            if (Math.sqrt(n) == (int) Math.sqrt(n)) return 1;
            // 判断n是否为完全平方数(满足平方和公式n = i^2 + j^2，即n - i^2是否为完全平方数)，只需2个完全平方数i^2和j^2
            for (int i = 1; i * i <= n; i++) {
                if (Math.sqrt(n - i * i) == (int) Math.sqrt(n - i * i)) return 2;
            }
            // 只剩3
            return 3;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：转换为背包/凑零钱问题
     * 1. 本质：用一些完全平方数(1,4,9...)凑成正整数n，求最少需要几个。
     * 2. 定义：dp[i] 表示凑成正整数 i 所需的最少完全平方数个数
     * 3. 状态转移方程：dp[i] = min(dp[i], dp[i - j*j] + 1) (j*j <= i)
     * 4. 初始化：dp[0] = 0
     * 5. 结果：dp[n]
     * 时间复杂度：O(n*sqrt(n))
     * 空间复杂度：O(n)
     * */
    class Solution1 {
        public int numSquares(int n) {
            // dp[i]表示凑成正整数 i 所需的最少完全平方数个数
            int[] dp = new int[n + 1];
            // 计算和为[1,n]的完全平方数个数
            for (int i = 1; i <= n; i++) {
                // 初始化：凑成i最多需要i个完全平方数（都是1）
                dp[i] = i;
                // 逐个减去比i小的完全平方数，更新dp[i]
                for (int j = 1; j * j <= i; j++)
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
            return dp[n];
        }
    }


    public static void main(String[] args) {
        Solution solution = new PerfectSquares().new Solution();
        // put your test code here

    }
}