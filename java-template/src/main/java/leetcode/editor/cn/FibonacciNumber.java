package leetcode.editor.cn;

import java.util.Arrays;

public class FibonacciNumber {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路四：自底向上滚动更新前两个状态
     */
    class Solution {
        public int fib(int n) {
            if (n == 0 || n == 1)
                return n;
            int dp_i_1 = 1;
            int dp_i_2 = 0;
            for (int i = 2; i <= n; i++) {
                int dp_i = dp_i_1 + dp_i_2;
                // 滚动更新
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i_1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路三：自底向上构建dp数组
     */
    class Solution3 {
        public int fib(int n) {
            if (n == 0 || n == 1)
                return n;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++)
                dp[i] = dp[i - 1] + dp[i - 2];
            return dp[n];
        }
    }

    /**
     * 思路二：带备忘录的递归
     */
    class Solution2 {
        public int fib(int n) {
            int[] memo = new int[n + 1];
            Arrays.fill(memo, -1);
            return dp(memo, n);
        }

        int dp(int[] memo, int n) {
            if (n == 0 || n == 1)
                return n;
            if (memo[n] != -1)
                return memo[n];
            memo[n] = dp(memo, n - 1) + dp(memo, n - 2);
            return memo[n];
        }
    }

    /**
     * 思路一：递归
     */
    class Solution1 {
        public int fib(int n) {
            if (n == 0 || n == 1)
                return n;
            return fib(n - 1) + fib(n - 2);
        }
    }


    public static void main(String[] args) {
        Solution solution = new FibonacciNumber().new Solution();
        // put your test code here

    }
}