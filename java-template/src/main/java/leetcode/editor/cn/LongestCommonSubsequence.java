package leetcode.editor.cn;

import java.util.Arrays;

public class LongestCommonSubsequence {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 空间复杂度：O(N)
     */
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
            // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
            int[] dp = new int[n + 1];
            // base case: dp[0][..] = dp[..][0] = 0
            /*
             * i/j  j-1     j               j+1
             * i-1  prev    temp(prev)     temp
             * i    dp[j-1]   dp[j](dp[j-1])    dp[j]
             * */
            for (int i = 1; i <= m; i++) {
                // prev = dp[i-1][j-1]
                int prev = dp[0];
                for (int j = 1; j <= n; j++) {
                    // temp = dp[i-1][j]
                    int temp = dp[j];
                    // dp[j] = dp[i][j]
                    dp[j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? prev + 1 : Math.max(dp[j - 1], temp);
                    // 下一轮内循环j++,对下一个j来说,prev = temp(dp[i-1][j]) = dp[i-1][j-1]
                    prev = temp;
                }
            }
            return dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP Table
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution2 {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
            // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
            int[][] dp = new int[m + 1][n + 1];
            // base case: dp[0][..] = dp[..][0] = 0
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= n; j++)
                    dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            return dp[m][n];
        }
    }

    /**
     * 思路一：递归DP
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution1 {
        int[][] memo;

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(text1, 0, text2, 0);
        }

        // 定义:dp(s1,i,s2,j)表示s1[i..]和s2[j..]的最长公共子序列
        int dp(String s1, int i, String s2, int j) {
            // base case
            if (i == s1.length()) return 0;
            if (j == s2.length()) return 0;
            if (memo[i][j] != -1)
                return memo[i][j];
            if (s1.charAt(i) == s2.charAt(j))
                memo[i][j] = dp(s1, i + 1, s2, j + 1) + 1;
            else
                memo[i][j] = Math.max(dp(s1, i + 1, s2, j), dp(s1, i, s2, j + 1));
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new LongestCommonSubsequence().new Solution();
        // put your test code here
        solution.longestCommonSubsequence("abcde", "ace");
    }
}