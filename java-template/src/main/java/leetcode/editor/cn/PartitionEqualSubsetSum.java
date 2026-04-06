package leetcode.editor.cn;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路五：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 时间复杂度：O(N)
     */
    class Solution {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            if ((sum & 1) == 1)
                return false;
            sum = sum / 2;
            int m = nums.length;
            /*
             * 定义：dp[i][j]表示nums[0..i-1]中是否存在元素和等于j的子集
             * 目标：dp[m][sum]表示nums[0..m-1]中是否存在元素和等于sum的子集
             * base case:dp[0][..]=false,dp[..][0]=true
             * 状态转移:
             * 若nums[i-1]>j,dp[i][j] = dp[i-1][j]
             * 若nums[i-1]<=j,dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j]
             * i∈[1,m],j∈[0,sum]
             * i/j  j-nums[i-1]                 j
             * i-1  dp[i-1][j - nums[i - 1]]    dp[i-1][j]
             * i                                dp[i][j]
             * 遍历顺序：从上往下，从右往左
             * 为什么从右往左？
             * dp[i][j]依赖dp[i-1][j - nums[i - 1]]
             * j - nums[i - 1]在j的左边，如果从左往右，则j - nums[i - 1]先被更新为第i行
             * 故需要从右往左，先更新dp[j]为dp[i][j]，此时dp[j - nums[i - 1]]未被更新，仍然为dp[i-1][j-nums[i-1]]
             * */
            boolean[] dp = new boolean[sum + 1];
            for (int i = 1; i <= m; i++) {
                // base case
                dp[0] = true;
                for (int j = sum; j >= nums[i - 1]; j--)
                    dp[j] = dp[j - nums[i - 1]] || dp[j];
            }

            return dp[sum];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路四：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 时间复杂度：O(N)
     */
    class Solution4 {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            if ((sum & 1) == 1)
                return false;
            int n = sum / 2;
            int m = nums.length;
            /*
             * 定义：dp[i][j]表示nums[0..i-1]中小于j的最大元素和
             * 目标：dp[m][n]表示nums[0..m-1]中小于n的最大元素和
             * base case:dp[0][..]=0,dp[..][0]=0
             * 状态转移:
             * 若nums[i-1]>j,dp[i][j] = dp[i-1][j]
             * 若nums[i-1]<=j,dp[i][j] = max(dp[i-1][j-nums[i-1]] + nums[i-1],dp[i-1][j])
             * i∈[1,m],j∈[0,n]
             * i/j  j-nums[i-1]                 j
             * i-1  dp[i-1][j - nums[i - 1]]    dp[i-1][j]
             * i                                dp[i][j]
             * 遍历顺序：从上往下，从右往左
             * 为什么从右往左？
             * dp[i][j]依赖dp[i-1][j - nums[i - 1]]
             * j - nums[i - 1]在j的左边，如果从左往右，则j - nums[i - 1]先被更新为第i行
             * 故需要从右往左，先更新dp[j]为dp[i][j]，此时dp[j - nums[i - 1]]未被更新，仍然为dp[i-1][j-nums[i-1]]
             * */
            int[] dp = new int[n + 1];
            for (int i = 1; i <= m; i++)
                for (int j = n; j >= nums[i - 1]; j--)
                    dp[j] = Math.max(dp[j - nums[i - 1]] + nums[i - 1], dp[j]);

            return dp[n] == n;
        }
    }

    /**
     * 思路三：DP Table
     * 时间复杂度：O(MN)
     * 时间复杂度：O(MN)
     */
    class Solution3 {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            if ((sum & 1) == 1)
                return false;
            int n = sum / 2;
            int m = nums.length;
            /*
             * 定义：dp[i][j]表示nums[0..i-1]中小于j的最大元素和
             * 目标：dp[m][n]表示nums[0..m-1]中小于n的最大元素和
             * base case:dp[0][..]=0,dp[..][0]=0
             * 状态转移:
             * 若nums[i-1]>j,dp[i][j] = dp[i-1][j]
             * 若nums[i-1]<=j,dp[i][j] = max(dp[i-1][j-nums[i-1]] + nums[i-1],dp[i-1][j])
             * 遍历顺序：从上往下，从左往右
             * i∈[1,m],j∈[0,n]
             * */
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (nums[i - 1] > j)
                        dp[i][j] = dp[i - 1][j];
                    else
                        dp[i][j] = Math.max(dp[i - 1][j - nums[i - 1]] + nums[i - 1], dp[i - 1][j]);
                }
            }

            return dp[m][n] == n;
        }
    }

    /**
     * 思路二：递归DP + 备忘录
     * 时间复杂度：O(MN)
     * 时间复杂度：O(MN)
     */
    class Solution2 {
        int[][] memo;

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            if ((sum & 1) == 1)
                return false;
            int n = sum / 2;
            int m = nums.length;
            memo = new int[m][n + 1];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(nums, m - 1, n) == n;
        }

        // dp(nums,i,w)表示nums[0..i]中小于等于w的最大元素和
        int dp(int[] nums, int i, int w) {
            if (i < 0 || w == 0)
                return 0;
            if (memo[i][w] != -1)
                return memo[i][w];
            if (nums[i] > w)
                memo[i][w] = dp(nums, i - 1, w);
            else
                memo[i][w] = Math.max(dp(nums, i - 1, w - nums[i]) + nums[i], dp(nums, i - 1, w));
            return memo[i][w];
        }
    }

    /**
     * 思路一：回溯
     * 时间复杂度：O(2^N)
     * 时间复杂度：O(N)
     * 【Time Limit Exceeded】
     */
    class Solution1 {
        boolean found = false;

        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums)
                sum += num;
            if ((sum & 1) == 1)
                return false;
            int n = sum / 2;
            backtrack(nums, 0, 0, n);
            return found;
        }

        void backtrack(int[] nums, int idx, int sum, int target) {
            if (found)
                return;
            if (sum == target) {
                found = true;
                return;
            }
            for (int i = idx; i < nums.length; i++) {
                if (sum + nums[i] > target)
                    continue;
                backtrack(nums, i + 1, sum + nums[i], target);
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new PartitionEqualSubsetSum().new Solution();
        // put your test code here
        solution.canPartition(new int[]{1, 5, 11, 5});
    }
}