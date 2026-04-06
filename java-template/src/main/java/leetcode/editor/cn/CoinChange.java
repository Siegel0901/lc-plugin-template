package leetcode.editor.cn;

import java.util.Arrays;

public class CoinChange {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路三：迭代
         */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            // amount总额最多amount个硬币,amount+1永远无法达到
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            // 遍历所有状态
            for (int i = 0; i <= amount; i++) {
                // 遍历所有选择
                for (int coin : coins) {
                    // 子问题无解
                    if (i - coin < 0)
                        continue;
                    // 取子问题解的最小值
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
            return dp[amount] == amount + 1 ? -1 : dp[amount];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {
        /**
         * 思路二：递归+备忘录
         */
        int[] memo;

        public int coinChange(int[] coins, int amount) {
            memo = new int[amount + 1];
            // 备忘录初始化为一个不会被取到的特殊值，代表还未被计算
            Arrays.fill(memo, -2);

            return dp(coins, amount);
        }

        int dp(int[] coins, int amount) {
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            if (memo[amount] != -2)
                return memo[amount];
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int num = dp(coins, amount - coin);
                if (num == -1) continue;
                res = Math.min(res, num + 1);
            }
            memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
            return memo[amount];
        }
    }

    class Solution1 {
        /**
         * 思路一：递归【Time Limit Exceeded】
         */
        public int coinChange(int[] coins, int amount) {
            // base case
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            // res记录结果,coinChange返回总额为amount的最少硬币个数
            int res = Integer.MAX_VALUE;
            // 遍历每种选择
            for (int coin : coins) {
                // 选择导致amount状态改变,返回状态amount-coin的最少硬币个数num
                int num = coinChange(coins, amount - coin);
                // 若无结果,则跳过
                if (num == -1)
                    continue;
                // 更新最小结果,+1是因为当前状态amount需要选择一个coin硬币
                res = Math.min(res, num + 1);
            }
            // 返回结果
            return res == Integer.MAX_VALUE ? -1 : res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new CoinChange().new Solution();
        // put your test code here

    }
}