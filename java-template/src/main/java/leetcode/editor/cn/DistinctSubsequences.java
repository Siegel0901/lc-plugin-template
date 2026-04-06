package leetcode.editor.cn;

import java.util.Arrays;

public class DistinctSubsequences {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路四：迭代 + s的视角（空间优化）
         * 时间复杂度：O(MN)
         * 空间复杂度：O(N)
         * 二维DP数组：
         * i/j  0-r 1-a 2-b 3-b 4-i 5-t 6-∅
         * 0-r  3   3   3   3   1   1   1
         * 1-a  0   3   3   3   1   1   1
         * 2-b  0   0   3   3   1   1   1
         * 3-b  0   0   1   2   1   1   1
         * 4-b  0   0   0   1   1   1   1
         * 5-i  0   0   0   0   1   1   1
         * 6-t  0   0   0   0   0   1   1
         * 7-∅  0   0   0   0   0   0   1
         * 遍历顺序
         * i/j  6-∅ 5-t 4-i 3-b 2-b 1-a 0-r
         * 7-∅  1   0   0   0   0   0   0
         * 6-t  1   1   0   0   0   0   0
         * 5-i  1   1   1   0   0   0   0
         * 4-b  1   1   1   1   0   0   0
         * 3-b  1   1   1   2   1   0   0
         * 2-b  1   1   1   3   3   0   0
         * 1-a  1   1   1   3   3   3   0
         * 0-r  1   1   1   3   3   3   3
         *
         */
        public int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            int[] dp = new int[n + 1];
            dp[n] = 1;
            /*
            * dp[i][j]需要两个状态：dp[i+1][j+1]和dp[i+1][j]
            * 外层循环每循环一次i--
            * 内存循环每循环一次j--
            * 在对dp[j]赋值之前，dp[j]表示外层循环上一次迭代出来的值，也就是dp[i+1][j]
            * */
            for (int i = m - 1; i >= 0; i--) {
                // 保存dp[j+1]的旧值,即dp[i+1][j+1]
                int prev = dp[n];
                for (int j = n - 1; j >= 0; j--) {
                    // 保存当前dp[j],即dp[i+1][j]
                    int temp = dp[j];
                    if (s.charAt(i) == t.charAt(j))
                        // dp[i][j] = dp[i+1][j] + dp[i+1][j+1]
                        dp[j] = temp + prev;
                    // 为j-1做准备,temp = dp[i+1][j],是dp[i+1][j-1]的dp[i+1][j+1]
                    prev = temp;
                }
            }
            return dp[0];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution3 {
        /**
         * 思路三：迭代 + s的视角
         * 时间复杂度：O(MN)
         * 空间复杂度：O(MN)
         */
        public int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            /*
             * 定义：dp[i][j] = s[i..] 中 t[j..] 作为子序列的个数
             * 目标：dp[0][0] = s[0..] 中 t[0..] 作为子序列的个数
             * 初始化为[m+1][n+1]用来处理s[m..]和t[n..]的情况，此时s和t为空
             * */
            int[][] dp = new int[m + 1][n + 1];
            // base case1: t为空串，dp[i][n]=1
            for (int i = 0; i <= m; i++)
                dp[i][n] = 1;
            // base case2: s为空串,t不为空(可省略，默认为0)
            for (int j = 0; j < n; j++)
                dp[m][j] = 0;

            /*
             * 当s[i] != t[j]时,不匹配,i后移,dp[i][j] = dp[i+1][j]
             * 当s[i] == t[j]时，有匹配和不匹配两种情况,dp[i][j] = dp[i+1][j+1] + dp[i+1][j]
             * dp[i][j]的状态由dp[i+1][j]、dp[i+1][j+1]转移而来，故需要从下往上(m-1 -> 0)，从右向左遍历(n-1 -> 0)
             * */
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (s.charAt(i) == t.charAt(j))
                        dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                    else
                        dp[i][j] = dp[i + 1][j];
                }
            }

            return dp[0][0];
        }
    }

    class Solution2 {
        /**
         * 思路二：以s的视角进行递归dp + 备忘录
         * 时间复杂度：O(MN)
         */
        public int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(s, 0, t, 0);
        }

        int[][] memo;

        /*
         * 定义dp函数：s[i..]的子序列中t[j..]出现的个数为dp(s,i,t,j)
         * base case：
         * 1. 当s的长度比t小时，s的子序列中t出现的个数为0
         * 2. 当j == t.length时，说明在s的子序列中找到了一个t，出现的个数为1
         * */
        int dp(String s, int i, String t, int j) {
            // base case
            if (j == t.length())
                return 1;
            if (s.length() - i < t.length() - j)
                return 0;
            // 查备忘录
            if (memo[i][j] != -1)
                return memo[i][j];
            /*
             * 以s的视角出发，分解子问题：
             * 1. 观察s[i]是否与t[j]相等，若不相等，则子问题为dp(s,i+1,t,j)
             * 2. 若相等，则有匹配和不匹配两种情况，结果需要相加
             * 2.1. 匹配，子问题为dp(s,i+1,t,j+1)
             * 2.2. 不匹配，子问题为dp(s,i+1,t,j)
             * */
            if (s.charAt(i) == t.charAt(j))
                memo[i][j] = dp(s, i + 1, t, j + 1) + dp(s, i + 1, t, j);
            else
                memo[i][j] = dp(s, i + 1, t, j);

            return memo[i][j];
        }
    }

    class Solution1 {
        /**
         * 思路一：以t的视角进行递归dp + 备忘录
         * 时间复杂度：O(MN*M) = O(N*M^2)
         */
        public int numDistinct(String s, String t) {
            int m = s.length(), n = t.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(s, 0, t, 0);
        }

        int[][] memo;

        /*
         * 定义dp函数：s[i..]的子序列中t[j..]出现的个数为dp(s,i,t,j)
         * base case：
         * 1. 当s的长度比t小时，s的子序列中t出现的个数为0
         * 2. 当j == t.length时，说明在s的子序列中找到了一个t，出现的个数为1
         * */
        int dp(String s, int i, String t, int j) {
            // base case
            if (j == t.length())
                return 1;
            if (s.length() - i < t.length() - j)
                return 0;
            if (memo[i][j] != -1)
                return memo[i][j];
            /*
             * 以t的视角出发，分解子问题：
             * 在s中找到t[j]的位置s[k]，使得s[k]==t[j]
             * 则子问题为s[k+1..]的子序列中t[j+1..]出现的个数为dp(s,k+1,t,j+1)
             * */
            memo[i][j] = 0;
            for (int k = i; k < s.length(); k++)
                if (s.charAt(k) == t.charAt(j))
                    memo[i][j] += dp(s, k + 1, t, j + 1);
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new DistinctSubsequences().new Solution();
        // put your test code here
        solution.numDistinct("rabbbit", "rabbit");
        Solution3 solution3 = new DistinctSubsequences().new Solution3();
        solution3.numDistinct("rabbbit", "rabbit");
    }
}