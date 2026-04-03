package leetcode.editor.cn;

import java.util.Arrays;

public class EditDistance {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：DP Table
     * 1. dp[i][j]定义：dp[i][j]表示s1[0..i-1]和s2[0..j-1]的最小编辑距离
     * 2. 状态转移：
     * 2.1. 若s1[i-1] == s2[j-1]，则dp[i][j] = dp[i-1][j-1]
     * 2.2. 若s1[i-1] != s2[j-1]，可进行三种操作
     * 2.2.1. 插入,在s1[i-1]之后插入s2[j-1],s2[j-1]被匹配,j前移,结果为dp[i][j-1]+1
     * 2.2.2. 删除,删除s1[i-1],i前移,结果为dp[i-1][j]+1
     * 2.2.3. 替换,s2[j-1]的值赋值给s1[i-1],s1[i-1]和s2[j-1]匹配,i和j前移,结果为dp[i-1][j-1]+1
     * 2.2.4. dp[i][j]表示最小的编辑距离，故dp[i][j] = min(dp[i][j-1]+1,dp[i-1][j]+1,dp[i-1][j-1]+1)
     * 3. base case：当某个字符串匹配完，需要删除另一个字符串的未匹配字符个数
     * 4. dp[i][j]依赖于dp[i][j-1],dp[i-1][j]和dp[i-1][j-1]，故需要从左往右，从上往下遍历
     */
    class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            int[][] dp = new int[m + 1][n + 1];
            // base case
            for (int i = 0; i <= m; i++)
                dp[i][0] = i;
            for (int j = 0; j <= n; j++)
                dp[0][j] = j;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        dp[i][j] = dp[i - 1][j - 1];
                    else
                        dp[i][j] = min(dp[i][j - 1] + 1, dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1);
                }
            }
            return dp[m][n];
        }

        int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：递归dp
     * 1. dp函数定义：dp(s1,i,s2,j)表示s1[0..i]和s2[0..j]的最小编辑距离
     * 2. 状态转移：
     * 2.1. 若s1[i] == s2[j]，则dp(s1,i,s2,j) = dp(s1,i-1,s2,j-1)
     * 2.2. 若s1[i] != s2[j]，可进行三种操作
     * 2.2.1. 插入,在s1[i]之后插入s2[j],s2[j]被匹配,j前移,结果为dp(s1,i,s2,j-1)+1
     * 2.2.2. 删除,删除s1[i],i前移,结果为dp(s1,i-1,s2,j)+1
     * 2.2.3. 替换,s2[j]的值赋值给s1[i],s1[i]和s2[j]匹配,i和j前移,结果为dp(s1,i-1,s2,j-1)+1
     * 2.2.4. dp(s1,i,s2,j)表示最小的编辑距离，故dp(s1,i,s2,j) = min(dp(s1,i,s2,j-1)+1,dp(s1,i-1,s2,j)+1,dp(s1,i-1,s2,j-1)+1)
     * 3. base case：当某个字符串匹配完，需要删除另一个字符串的未匹配字符个数
     */
    class Solution1 {
        int[][] memo;

        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(word1, m - 1, word2, n - 1);
        }

        // 定义:返回s1[0..i]和s2[0..j]的最小编辑距离
        int dp(String s1, int i, String s2, int j) {
            // base case
            if (i == -1) return j + 1;
            if (j == -1) return i + 1;

            // 查备忘录
            if (memo[i][j] != -1)
                return memo[i][j];

            if (s1.charAt(i) == s2.charAt(j)) {
                // 相等，则直接跳过
                memo[i][j] = dp(s1, i - 1, s2, j - 1);
            } else {
                // 不相等：取插入，删除，替换操作的最小值
                memo[i][j] = min(
                        // 插入,在s1[i]之后插入s2[j],s2[j]被匹配,j前移
                        dp(s1, i, s2, j - 1) + 1,
                        // 删除,删除s1[i],i前移
                        dp(s1, i - 1, s2, j) + 1,
                        // 替换,s2[j]的值赋值给s1[i],s1[i]和s2[j]匹配,i和j前移
                        dp(s1, i - 1, s2, j - 1) + 1
                );
            }
            return memo[i][j];
        }

        int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }

    }


    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
        // put your test code here

    }
}