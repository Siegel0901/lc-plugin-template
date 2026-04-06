package leetcode.editor.cn;

import java.util.Arrays;

public class MinimumAsciiDeleteSumForTwoStrings {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 空间复杂度：O(min(M,N))
     */
    class Solution {
        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            if (m > n)
                return minimumDeleteSum(s2, s1);
            // 定义:dp[i][j]表示使s1[0..i-1]和s2[0..j-1]相等所需删除字符的ASCII值的最小和
            // 目标:dp[m][n]表示使s1[0..m-1]和s2[0..n-1]相等所需删除字符的ASCII值的最小和
            // prev表示第i-1层
            int[] prev = new int[n + 1];
            // curr表示第i层
            int[] curr = new int[n + 1];
            // base case:s1为空
            for (int j = 1; j <= n; j++)
                prev[j] = prev[j - 1] + s2.charAt(j - 1);
            /*
             * dp[i][j]依赖的状态：
             * i/j          j-1             j
             * i-1(prev)    dp[i-1][j-1]    dp[i-1][j]
             * i(curr)      dp[i][j-1]      dp[i][j]
             * 遍历顺序：从上到下，从左到右
             * */
            for (int i = 1; i <= m; i++) {
                curr[0] = prev[0] + s1.charAt(i - 1);
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        curr[j] = prev[j - 1];
                    else
                        curr[j] = Math.min(prev[j] + s1.charAt(i - 1), curr[j - 1] + s2.charAt(j - 1));
                }
                int[] temp = prev;
                prev = curr;
                curr = temp;
            }
            return prev[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路二：DP Table
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution2 {
        public int minimumDeleteSum(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            // 定义:dp[i][j]表示使s1[0..i-1]和s2[0..j-1]相等所需删除字符的ASCII值的最小和
            // 目标:dp[m][n]表示使s1[0..m-1]和s2[0..n-1]相等所需删除字符的ASCII值的最小和
            int[][] dp = new int[m + 1][n + 1];
            // base case:s1或s2为空
            for (int i = 1; i <= m; i++)
                dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
            for (int j = 1; j <= n; j++)
                dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
            /*
             * dp[i][j]依赖的状态：
             * i/j   j-1             j
             * i-1   dp[i-1][j-1]    dp[i-1][j]
             * i     dp[i][j-1]      dp[i][j]
             * 遍历顺序：从上到下，从左到右
             * */
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
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
        int m;
        int n;

        public int minimumDeleteSum(String s1, String s2) {
            m = s1.length();
            n = s2.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(s1, 0, s2, 0);
        }

        // 定义:dp(s1,i,s2,j)表示使s1[i..]和s2[j..]相等所需删除字符ASCII值的最小和
        int dp(String s1, int i, String s2, int j) {
            // base case:s1为空，需要删除s2剩余的字符
            if (i == m) {
                int res = 0;
                for (; j < n; j++)
                    res += s2.charAt(j);
                return res;
            }
            // base case:s2为空,需要删除s1剩余的字符
            if (j == n) {
                int res = 0;
                for (; i < m; i++)
                    res += s1.charAt(i);
                return res;
            }
            // 查备忘录
            if (memo[i][j] != -1)
                return memo[i][j];
            // s1[i]和s2[j]相同,则不需要删除,i和j后移
            if (s1.charAt(i) == s2.charAt(j)) {
                memo[i][j] = dp(s1, i + 1, s2, j + 1);
            } else {
                /*
                 * s1[i]和s2[j]不相同,则需要删除一个,取最小和
                 * 为什么不考虑同时删除的情况?
                 * 因为同时删除是冗余的,先删s1[i]后删s2[j]可以做到同样的效果,反之亦然
                 * */
                memo[i][j] = Math.min(
                        dp(s1, i + 1, s2, j) + s1.charAt(i),
                        dp(s1, i, s2, j + 1) + s2.charAt(j)
                );
            }
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new MinimumAsciiDeleteSumForTwoStrings().new Solution();
        // put your test code here
        solution.minimumDeleteSum("delete", "leet");
    }
}