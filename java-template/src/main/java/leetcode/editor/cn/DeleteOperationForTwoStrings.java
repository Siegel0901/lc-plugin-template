package leetcode.editor.cn;

import java.util.Arrays;

public class DeleteOperationForTwoStrings {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路四：求最长公共子序列
     * 1. 两个单词的相同部分就是最长公共子序列
     * 2. 求出最长公共子序列后多余的字符就是需要删除的字符
     * 时间复杂度：O(MN)
     * 空间复杂度：O(N)
     */
    class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            /*
             * dp[i][j]表示s1[0,i-1]和s2[0,j-1]的最长公共子序列
             * base case：
             * i||j==0时，LCS = 0
             * 状态转移方程：
             * s1[i-1] == s2[j-1]：dp[i][j] = dp[i-1][j-1]+1
             * s1[i-1] != s2[j-1]：dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j],dp[i-1][j-1])
             * 由于dp[i-1][j-1]长度比dp[i][j-1],dp[i-1][j]小，故值一定比dp[i][j-1],dp[i-1][j]小
             * 所以s1[i-1] != s2[j-1]：dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j])
             * 空间压缩：
             * dp[i][j]依赖的状态:dp[i-1][j-1],dp[i-1][j],dp[i][j-1]
             * i/j   j-1         j           j+1
             * i-1   prev    temp(prev)      temp
             * i     dp[j-1] dp[j](dp[j-1])  dp[j]
             * 遍历方向:从上往下，从左往右
             * */
            // base case 初始值默认为0
            int[] dp = new int[n + 1];
            // 从上往下，从左往右遍历
            for (int i = 1; i <= m; i++) {
                // prev = dp[i-1][j-1]
                int prev = dp[0];
                for (int j = 1; j <= n; j++) {
                    // temp = dp[i-1][j]
                    int temp = dp[j];
                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        // dp[i][j] = dp[i-1][j-1]+1
                        dp[j] = prev + 1;
                    else
                        // dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])
                        dp[j] = Math.max(temp, dp[j - 1]);
                    prev = temp;
                }
            }
            // m和n分别减去LCS的长度即为要删除的字符数
            return m - dp[n] + n - dp[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路三：DP Table + 压缩空间
     * 因为base case分别初始化了二维数组的第一行和第一列，无法压缩成一维数组同时表达这两个边界条件
     * 故需要用两个一维数组进行压缩
     * 时间复杂度：O(MN)
     * 空间复杂度：O(min(M,N))
     */
    class Solution3 {
        /*
         * i/j   0   1e  2a  3t
         * 0     0   1   2   3
         * 1s    1   2   3   4
         * 2e    2   1   2   3
         * 3a    3   2   1   2
         * */
        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            // 确保空间复杂度为O(min(M,N))
            if (m < n)
                return minDistance(word2, word1);
            // prev表示第i-1行
            // curr表示第i行
            int[] prev = new int[n + 1];
            int[] curr = new int[n + 1];
            // base case: word1为空
            for (int j = 0; j <= n; j++)
                // dp[i - 1][j]
                prev[j] = j;
            for (int i = 1; i <= m; i++) {
                // base case: word2为空
                // dp[i][0]
                curr[0] = i;
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1))
                        // dp[i][j] = dp[i-1][j-1]
                        curr[j] = prev[j - 1];
                    else
                        // dp[i][j] = Math.min(dp[i-1][j] + 1,dp[i][j-1]+1)
                        curr[j] = Math.min(prev[j] + 1, curr[j - 1] + 1);
                }
                /*
                 * 为什么这里需要交换数组？
                 * 1. prev和curr在二维数组中的位置需要整体往下移，故需要将curr中的值赋值给prev，prev始终表示第i-1行，curr始终表示第i行
                 * 2. curr的值会在下一轮循环中被覆盖，所以curr中的值赋值给prev之后被记录下来就行
                 * 3. 使用交换数组的方式是最高效的，只需要交换索引O(1)的复杂度，不需要复制数组什么的
                 * 4. prev = curr之后，不可以不管curr，因为此时prev和curr指向同一个数组，改了curr之后就会修改prev，所以curr需要指向别处
                 * */
                int[] temp = prev;
                prev = curr;
                curr = temp;
            }
            return prev[n];
        }
    }

    /**
     * 思路二：DP Table
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    class Solution2 {

        public int minDistance(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            // 定义:dp[i][j]表示使s1[0..i-1]和s2[0..j-1]相同所需的最小步数
            // 目标:dp[m][n]表示使s1[0..m-1]和s2[0..n-1]相同所需的最小步数
            int[][] dp = new int[m + 1][n + 1];
            // base case
            for (int i = 0; i <= m; i++)
                dp[i][0] = i;
            for (int j = 0; j <= n; j++)
                dp[0][j] = j;
            for (int i = 1; i <= m; i++)
                for (int j = 1; j <= n; j++)
                    dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
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
        int m, n;

        public int minDistance(String word1, String word2) {
            m = word1.length();
            n = word2.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(word1, 0, word2, 0);
        }

        // 定义:dp(s1,i,s2,j)表示使s1[i..]和s2[j..]相同所需的最小步数
        int dp(String s1, int i, String s2, int j) {
            // base case
            if (i == m)
                return n - j;
            if (j == n)
                return m - i;
            if (memo[i][j] != -1)
                return memo[i][j];
            if (s1.charAt(i) == s2.charAt(j))
                memo[i][j] = dp(s1, i + 1, s2, j + 1);
            else
                memo[i][j] = Math.min(dp(s1, i + 1, s2, j) + 1, dp(s1, i, s2, j + 1) + 1);
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
        // put your test code here
        solution.minDistance("sea", "eat");
    }
}