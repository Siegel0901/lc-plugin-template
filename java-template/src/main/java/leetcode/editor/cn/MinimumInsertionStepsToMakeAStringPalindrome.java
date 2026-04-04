package leetcode.editor.cn;

public class MinimumInsertionStepsToMakeAStringPalindrome {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：求最长回文子序列
     * 1. 最长回文子序列的长度为n
     * 2. 则最少操作次数为s.length() - n
     * 时间复杂度：O(M^2)
     * 空间复杂度：O(M)
     */
    class Solution {
        public int minInsertions(String s) {
            int m = s.length();
            /*
             * 定义：dp[i][j]表示s[i..j]的最长回文子序列
             * 目标：dp[0][m-1]表示s[0..m-1]的最长回文子序列
             * 状态dp[i][j]依赖dp[i+1][j-1],dp[i+1][j],dp[i][j-1]
             * i/j  j-1                 j
             * i    dp[i][j-1](dp[j-1]) dp[i][j](dp[j])
             * i+1  dp[i+1][j-1](prev)  dp[i+1][j](temp)
             * 遍历顺序：从下往上，从左往右
             * i<j -> i∈[0,m-1],j∈[i+1,m-1]
             * base case:dp[i][j] = 1
             * */
            int[] dp = new int[m];
            for (int i = m - 1; i >= 0; i--) {
                // prev = dp[i+1][i] = dp[i+1][j-1]
                int prev = dp[i];
                for (int j = i + 1; j <= m - 1; j++) {
                    // temp = dp[i+1][j]
                    int temp = dp[j];
                    if (s.charAt(i) == s.charAt(j))
                        // dp[i][j] = dp[i+1][j-1] + 2
                        dp[j] = prev + 2;
                    else
                        // dp[i][j] = max(dp[i][j-1], dp[i+1][j])
                        dp[j] = Math.max(dp[j - 1], temp);
                    // prev = dp[i+1][j]，对下一个j来说prev = dp[i+1][j-1]
                    prev = temp;
                }
                // base case: i==j时,dp[i][j] = 1
                dp[i] = 1;
            }
            return s.length() - dp[m - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumInsertionStepsToMakeAStringPalindrome().new Solution();
        // put your test code here

    }
}