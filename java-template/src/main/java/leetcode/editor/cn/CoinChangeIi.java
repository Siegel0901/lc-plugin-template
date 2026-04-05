package leetcode.editor.cn;

import java.util.Arrays;

public class CoinChangeIi {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 压缩空间
     * 时间复杂度：O(MN)
     * 空间复杂度：O(N)
     */
    class Solution {
        public int change(int amount, int[] coins) {
            int m = coins.length;
            /*
             * 定义：dp[i][j]表示用coins[0..i-1]的硬币组合成j的组合数
             * 目标：dp[m][amount]表示用coins[0..m-1]的硬币组合成amount的组合数
             * */
            int[] dp = new int[amount + 1];
            /*
             * base case:
             * 1. dp[0][..] = 0，没有硬币可选
             * 2. dp[..][0] = 1，只有一种组合：什么都不选
             * */
            dp[0] = 1;
            /*
             * 状态转移：
             * coins[i-1]>j: dp[i][j] = dp[i-1][j]
             * coins[i-1]<=j: dp[i][j] = dp[i][j-coins[i-1] + dp[i-1][j]
             * i/j  j-coins[i-1]            j
             * i-1                          dp[i-1][j]
             * i    dp[i][j-coins[i-1]]     dp[i][j]
             * 遍历顺序：从上往下，从左往右
             * 为什么0-1背包依赖dp[i-1][j-coins[i-1]]时需要从右往左，而完全背包依赖dp[i][j-coins[i-1]]需要从左往右?
             * 本质还是所依赖的值是否更新的问题
             * 0-1背包从右往左，遍历到dp[j]时，dp[j-coins[i-1]]未更新，表示二维的dp[i-1][j-coins[i-1]]
             * 完全背包从左往右，遍历到dp[j]时，dp[j-coins[i-1]]已更新，表示二维的dp[i][j-coins[i-1]]
             * i∈[1,m],j∈[0,amount]
             * */
            for (int i = 1; i <= m; i++)
                for (int j = coins[i - 1]; j <= amount; j++)
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
            return dp[amount];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP Table
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution2 {
        public int change(int amount, int[] coins) {
            int m = coins.length;
            /*
             * 定义：dp[i][j]表示用coins[0..i-1]的硬币组合成j的组合数
             * 目标：dp[m][amount]表示用coins[0..m-1]的硬币组合成amount的组合数
             * */
            int[][] dp = new int[m + 1][amount + 1];
            /*
             * base case:
             * 1. dp[0][..] = 0，没有硬币可选
             * 2. dp[..][0] = 1，只有一种组合：什么都不选
             * */
            for (int i = 0; i <= m; i++)
                dp[i][0] = 1;
            /*
             * 状态转移：
             * coins[i-1]>j: dp[i][j] = dp[i-1][j]
             * coins[i-1]<=j: dp[i][j] = dp[i][j-coins[i-1] + dp[i-1][j]
             * i/j  j-coins[i-1]            j
             * i-1                          dp[i-1][j]
             * i    dp[i][j-coins[i-1]]     dp[i][j]
             * 遍历顺序：从上往下，从左往右
             * i∈[1,m],j∈[0,amount]
             * */
            for (int i = 1; i <= m; i++) {
                for (int j = 0; j <= amount; j++) {
                    if (coins[i - 1] > j)
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
            return dp[m][amount];
        }
    }

    /**
     * 思路一：递归DP
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution1 {
        int[][] memo;

        public int change(int amount, int[] coins) {
            int m = coins.length;
            memo = new int[m + 1][amount + 1];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(coins, m, amount);
        }

        // 返回前i个硬币，能凑成j的组合数
        int dp(int[] coins, int i, int j) {
            // base case
            if (i == 0)
                return 0;
            if (j == 0)
                return 1;
            // 查备忘录
            if (memo[i][j] != -1)
                return memo[i][j];
            if (coins[i - 1] > j)
                // 最后一个硬币大于总额，不选
                memo[i][j] = dp(coins, i - 1, j);
            else
                // 选or不选最后一个硬币，统计组合数
                memo[i][j] = dp(coins, i - 1, j) + dp(coins, i, j - coins[i - 1]);
            return memo[i][j];
        }
    }

    public static void main(String[] args) {
        Solution solution = new CoinChangeIi().new Solution();
        // put your test code here

    }
}