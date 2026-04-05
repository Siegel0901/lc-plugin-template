package leetcode.editor.cn;

import java.util.Arrays;

public class HouseRobber {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 空间压缩
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            /*
             * dp[i]表示nums[0,i-1]在不报警的情况下一夜之内能够偷窃到的最高金额
             * dp[n]表示nums[0,n-1]在不报警的情况下一夜之内能够偷窃到的最高金额
             * */
            // base case
            int dp_i = 0;
            int dp_i_1 = 0;
            int dp_i_2 = 0;
            /*
             * 状态转移：
             * dp[i] = max(dp[i-1],dp[i-2]+nums[i-1])
             * dp_i = Math.max(dp_i_1, dp_i_2 + nums[i - 1])
             * 遍历顺序：从左到右
             * i∈[1,n]
             * i-2      i-1             i               i+1
             * dp_i_2   dp_i_1(dp_i_2)  dp_i(dp_i_1)    (dp_i)
             * */
            for (int i = 1; i <= n; i++) {
                dp_i = Math.max(dp_i_1, dp_i_2 + nums[i - 1]);
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP Table
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    class Solution2 {
        public int rob(int[] nums) {
            int n = nums.length;
            /*
             * dp[i]表示nums[0,i-1]在不报警的情况下一夜之内能够偷窃到的最高金额
             * dp[n]表示nums[0,n-1]在不报警的情况下一夜之内能够偷窃到的最高金额
             * */
            int[] dp = new int[n + 1];
            // base case
            dp[0] = 0;
            dp[1] = nums[0];
            /*
             * 状态转移：
             * dp[i] = max(dp[i-1],dp[i-2]+nums[i-1])
             * 遍历顺序：从左到右
             * i∈[2,n]
             * */
            for (int i = 2; i <= n; i++)
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            return dp[n];
        }
    }

    /**
     * 思路一：递归DP
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    class Solution1 {
        int[] memo;

        public int rob(int[] nums) {
            memo = new int[nums.length];
            Arrays.fill(memo, -1);
            return dp(nums, 0);
        }

        // dp(nums,i)表示nums[i..]在不报警的情况下一夜之内能够偷窃到的最高金额
        int dp(int[] nums, int i) {
            // base case
            if (i >= nums.length)
                return 0;
            if (memo[i] != -1)
                return memo[i];
            memo[i] = Math.max(dp(nums, i + 1), dp(nums, i + 2) + nums[i]);
            return memo[i];
        }
    }


    public static void main(String[] args) {
        Solution solution = new HouseRobber().new Solution();
        // put your test code here

    }
}