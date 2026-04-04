package leetcode.editor.cn;

import java.util.Arrays;

public class MinimumFallingPathSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int[][] dp = new int[n][n];
            for (int j = 0; j < n; j++)
                dp[0][j] = matrix[0][j];

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == 0)
                        dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                    else if (j == n - 1)
                        dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                    else
                        dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1]));
                }
            }
            int res = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++)
                res = Math.min(res, dp[n - 1][j]);
            return res;
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        // 备忘录
        int[][] memo;

        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int res = Integer.MAX_VALUE;
            // 初始化备忘录
            memo = new int[n][n];
            for (int[] vals : memo)
                Arrays.fill(vals, Integer.MAX_VALUE);
            // 终点可能落到最后一行的任意一列
            for (int j = 0; j < n; j++)
                res = Math.min(res, dp(matrix, n - 1, j));
            return res;
        }

        // 定义：从第一行matrix[0][...]往下落，落到matrix[i][j]的最小路径和为dp(matrix,i,j)
        int dp(int[][] matrix, int i, int j) {
            // 检查非法索引
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix.length)
                return Integer.MAX_VALUE;
            // base case
            if (i == 0)
                return matrix[0][j];
            // 查备忘录
            if (memo[i][j] != Integer.MAX_VALUE)
                return memo[i][j];
            // (i,j)的状态由上一行三个方向的状态转移而来
            memo[i][j] = matrix[i][j] + min(
                    dp(matrix, i - 1, j - 1),
                    dp(matrix, i - 1, j),
                    dp(matrix, i - 1, j + 1)
            );
            return memo[i][j];
        }

        int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }
    }


    public static void main(String[] args) {
        Solution solution = new MinimumFallingPathSum().new Solution();
        // put your test code here

    }
}