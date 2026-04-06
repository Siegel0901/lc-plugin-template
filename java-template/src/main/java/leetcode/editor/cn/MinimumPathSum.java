package leetcode.editor.cn;

import java.util.Arrays;

public class MinimumPathSum {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 空间复杂度：O(N)
     */
    class Solution {
        public int minPathSum(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            // dp[i][j]表示从grid[0][0]到grid[i][j]的最小路径和
            // dp[m-1][n-1]表示从grid[0][0]到grid[m-1][n-1]的最小路径和
            int[] dp = new int[n];
            // base case i==0 || j==0,只能求和一路走到底
            dp[0] = grid[0][0];
            for (int j = 1; j < n; j++)
                dp[j] = dp[j - 1] + grid[0][j];
            /*
             * 状态转移
             * dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + grid[i][j]
             * i/j   j-1         j
             * i-1               dp[i-1][j]
             * i     dp[i][j-1]  dp[i][j]
             * 遍历方向：从上往下，从左往右
             * i∈[1,m-1],j∈[1,n-1]
             * */
            for (int i = 1; i < m; i++) {
                dp[0] += grid[i][0];
                for (int j = 1; j < n; j++)
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
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
        public int minPathSum(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            // dp[i][j]表示从grid[0][0]到grid[i][j]的最小路径和
            // dp[m-1][n-1]表示从grid[0][0]到grid[m-1][n-1]的最小路径和
            int[][] dp = new int[m][n];
            // base case i==0 || j==0,只能求和一路走到底
            dp[0][0] = grid[0][0];
            for (int i = 1; i < m; i++)
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            for (int j = 1; j < n; j++)
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            /*
             * 状态转移
             * dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + grid[i][j]
             * i/j   j-1         j
             * i-1               dp[i-1][j]
             * i     dp[i][j-1]  dp[i][j]
             * 遍历方向：从上往下，从左往右
             * i∈[1,m-1],j∈[1,n-1]
             * */
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
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

        public int minPathSum(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(grid, m - 1, n - 1);
        }

        // dp(grid,i,j)表示从(0,0)到(i,j)的最小路径和
        int dp(int[][] grid, int i, int j) {
            if (i == 0 && j == 0)
                return grid[0][0];
            if (i < 0 || j < 0)
                return Integer.MAX_VALUE;
            if (memo[i][j] != -1)
                return memo[i][j];
            memo[i][j] = Math.min(dp(grid, i - 1, j), dp(grid, i, j - 1)) + grid[i][j];
            return memo[i][j];
        }

    }


    public static void main(String[] args) {
        Solution solution = new MinimumPathSum().new Solution();
        // put your test code here

    }
}