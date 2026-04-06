package leetcode.editor.cn;

import java.util.Arrays;

public class UniquePaths {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 空间复杂度：O(N)
     */
    class Solution {
        public int uniquePaths(int m, int n) {
            // dp[i][j]表示从[0,0]走到[i,j]不同路径的数量
            // dp[m-1][n-1]表示从[0,0]走到[m-1,n-1]不同路径的数量
            int[] dp = new int[n];
            // base case:dp[i][0] = 1,dp[0][j] = 1
            Arrays.fill(dp, 1);
            /*
             * 状态转移
             * dp[i][j] = dp[i-1][j]+dp[i][j-1]
             * i/j  j-1         j
             * i-1              dp[i-1][j]
             * i    dp[i][j-1]  dp[i][j]
             * 遍历方向:从上往下，从左往右
             * i∈[1,m],j∈[1,n]
             * */
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    dp[j] += dp[j - 1];
            return dp[n - 1];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP Table
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution2 {
        public int uniquePaths(int m, int n) {
            // dp[i][j]表示从[0,0]走到[i,j]不同路径的数量
            // dp[m-1][n-1]表示从[0,0]走到[m-1,n-1]不同路径的数量
            int[][] dp = new int[m][n];
            // base case:dp[i][0] = 1,dp[0][j] = 1
            for (int i = 0; i < m; i++)
                dp[i][0] = 1;
            for (int j = 0; j < n; j++)
                dp[0][j] = 1;
            /*
             * 状态转移
             * dp[i][j] = dp[i-1][j]+dp[i][j-1]
             * i/j  j-1         j
             * i-1              dp[i-1][j]
             * i    dp[i][j-1]  dp[i][j]
             * 遍历方向:从上往下，从左往右
             * i∈[1,m],j∈[1,n]
             * */
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            return dp[m - 1][n - 1];
        }
    }

    /**
     * 思路一：递归DP
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution1 {
        int[][] memo;

        public int uniquePaths(int m, int n) {
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(m, n, 0, 0);
        }

        // dp(i,j)表示从[i,j]走到[m-1,n-1]不同路径的数量
        int dp(int m, int n, int i, int j) {
            // base case
            if (i >= m || j >= n)
                return 0;
            if (i == m - 1 && j == n - 1)
                return 1;
            if (memo[i][j] != -1)
                return memo[i][j];
            memo[i][j] = dp(m, n, i + 1, j) + dp(m, n, i, j + 1);
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        // put your test code here

    }
}