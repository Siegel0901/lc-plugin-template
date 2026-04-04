package leetcode.editor.cn;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路四：DP Table + 空间压缩(一个一维数组)
     * 时间复杂度：O(M^2)
     * 空间复杂度：O(M)
     */
    class Solution {
        public int longestPalindromeSubseq(String s) {
            int m = s.length();
            // 定义:dp[i][j]表示s[i,j]的最长回文子序列
            // 目标:dp[0][m-1]表示s[0,m-1]的最长回文子序列
            int[] dp = new int[m];

            /*
             * dp[i][j]依赖的状态：
             * dp[i+1][j-1],dp[i+1][j],dp[i][j-1]
             * i/j          j-1                     j                   j+1
             * i(dp)    dp[i][j-1](dp[j-1])     dp[i][j](dp[j])     dp[i][j+1]
             * i+1      dp[i+1][j-1](prev)      dp[i+1][j](temp)    dp[i+1][j+1]
             * 遍历顺序：从下到上，从左到右
             * 需要满足 i < j即i∈[0,m-1],j∈[i+1,m-1]
             * */
            for (int i = m - 1; i >= 0; i--) {
                /*
                 * prev = dp[i+1][i]
                 * j∈[i+1,m],i = j-1
                 * 故prev = dp[i+1][j-1]
                 * */
                int prev = dp[i];
                for (int j = i + 1; j <= m - 1; j++) {
                    // temp = dp[i+1][j]
                    int temp = dp[j];
                    if (s.charAt(i) == s.charAt(j))
                        // dp[i][j] = dp[i+1][j-1]+2
                        dp[j] = prev + 2;
                    else
                        // dp[i][j] = max(dp[i][j-1],dp[i+1][j])
                        dp[j] = Math.max(dp[j - 1], temp);
                    // prev后移，更新为dp[i+1][j]，对下一个j来说是dp[i+1][j-1]
                    prev = temp;
                }
                // base case:i==j,dp[i][j]=1
                dp[i] = 1;
            }
            return dp[m - 1];
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 空间压缩(两个一维数组）
     * 时间复杂度：O(M^2)
     * 空间复杂度：O(M)
     */
    class Solution3 {
        public int longestPalindromeSubseq(String s) {
            int m = s.length();
            // 定义:dp[i][j]表示s[i,j]的最长回文子序列
            // 目标:dp[0][m-1]表示s[0,m-1]的最长回文子序列
            // prev始终表示第i+1层
            int[] prev = new int[m];
            // curr始终表示第i层
            int[] curr = new int[m];

            /*
             * dp[i][j]依赖的状态：
             * dp[i+1][j-1],dp[i+1][j],dp[i][j-1]
             * i/j          j-1                     j                   j+1
             * i-1          dp[i-1][j-1]            dp[i-1][j]          dp[i-1][j+1]
             * i(curr)      dp[i][j-1](curr[j-1])   dp[i][j](curr[j])   dp[i][j+1]
             * i+1(prev)    dp[i+1][j-1](prev[j-1]) dp[i+1][j](prev[j]) dp[i+1][j+1]
             * 遍历顺序：从下到上，从左到右
             * 需要满足 i < j即i∈[0,m-1],j∈[i+1,m-1]
             * */
            for (int i = m - 1; i >= 0; i--) {
                // base case:i == j时,dp[i][j]=curr[j]=curr[i]=1
                curr[i] = 1;
                for (int j = i + 1; j <= m - 1; j++) {
                    if (s.charAt(i) == s.charAt(j))
                        curr[j] = prev[j - 1] + 2;
                    else
                        curr[j] = Math.max(curr[j - 1], prev[j]);
                }
                int[] temp = prev;
                prev = curr;
                curr = temp;
            }
            return prev[m - 1];
        }

    }


    /**
     * 思路二：DP Table
     * 时间复杂度：O(M^2)
     * 空间复杂度：O(M^2)
     */
    class Solution2 {
        public int longestPalindromeSubseq(String s) {
            int m = s.length();
            // 定义:dp[i][j]表示s[i,j]的最长回文子序列
            // 目标:dp[0][m-1]表示s[0,m-1]的最长回文子序列
            int[][] dp = new int[m][m];
            // base case:i > j,i == j,dp[i][j]=1
            for (int i = 0; i < m; i++)
                dp[i][i] = 1;
            /*
             * dp[i][j]依赖的状态：
             * dp[i+1][j-1],dp[i+1][j],dp[i][j-1]
             * i/j   j-1             j
             * i     dp[i][j-1]      dp[i][j]
             * i+1   dp[i+1][j-1]    dp[i+1][j]
             * 遍历顺序：从下到上，从左到右
             * 需要满足 i < j即i∈[0,m-1],j∈[i+1,m-1]
             * */
            for (int i = m - 1; i >= 0; i--) {
                for (int j = i + 1; j <= m - 1; j++) {
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    else
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
            return dp[0][m - 1];
        }
    }

    /**
     * 思路一：递归DP
     * 时间复杂度：O(M^2)
     * 空间复杂度：O(M^2)
     */
    class Solution1 {
        int[][] memo;
        int m;

        public int longestPalindromeSubseq(String s) {
            m = s.length();
            memo = new int[m][m];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(s, 0, m - 1);
        }

        // dp(s,i,j)表示s[i..j]的最长回文子序列
        int dp(String s, int i, int j) {
            // base case:下标越界
            if (i < 0 || j >= m || i > j)
                return 0;
            // base case:只有一个字符
            if (i == j)
                return 1;
            // 查备忘录
            if (memo[i][j] != -1)
                return memo[i][j];
            /*
             * 状态转移方程：
             * s[i] == s[j],dp(s,i,j) = dp(s,i+1,j-1)+2,最长回文子序列长度+2，同时i和j向中间移动
             * s[i] != s[j],判断s[i+1,j]或s[i,j-1]的最长回文子序列，取最大值
             * */
            if (s.charAt(i) == s.charAt(j))
                memo[i][j] = dp(s, i + 1, j - 1) + 2;
            else
                memo[i][j] = Math.max(dp(s, i + 1, j), dp(s, i, j - 1));
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence().new Solution();
        // put your test code here
        solution.longestPalindromeSubseq("bbbab");
    }
}