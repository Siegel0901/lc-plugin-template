package leetcode.editor.cn;

import java.util.Arrays;

public class PalindromicSubstrings {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路三：中心扩展判断回文
     * 1. 每个回文都有一个中心，从中心向两边扩展，遇到不相等就停止。
     * 2. 中心有2n-1个（n个字符 + (n-1)个字符间隙）
     * 3. 每个中心最多扩展到边界就停
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int countSubstrings(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                // 奇数长度回文，以i为中心
                res += expand(s, i, i);
                // 偶数长度回文，以i和i+1为中心
                res += expand(s, i, i + 1);
            }
            return res;
        }

        private int expand(String s, int l, int r) {
            // 记录回文串个数
            int cnt = 0;
            /*
             * 扩展原则：
             * 1. 确保l和r在范围内
             * 2. s[l]==s[r]
             * */
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                cnt++;
                l--;
                r++;
            }
            return cnt;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路二：DP Table
     * 1. 定义数组dp[i][j]，表示s[i..j]是否为回文子串
     * 2. 状态转移：dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
     * 3. 遍历所有(i,j)，统计dp[i][j]=true的个数
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * */
    class Solution2 {
        public int countSubstrings(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            /*
             * 为什么要全部赋值true?
             * 当i+1==j时，表示两个相邻字符，状态转移需要访问dp[i+1][j-1]，也就是dp[i+1][i]
             * 但i+1>i，区间无效，永远不会被遍历到，也就永远不会被赋值，所以要将默认的false改为true
             * 实际上dp[i+1][i]是一个空串，在状态转移中必须视为回文串
             * 两个相邻字符"s[i]s[j]"是否为回文的判断依据是s[i] == s[j] && ""空串是否为回文
             * 让空串百分百为回文，则判断依据就是s[i]是否等于s[j]
             * */
            for (boolean[] row : dp)
                Arrays.fill(row, true);
            int res = 0;
            /*
             * dp[i][j]依赖dp[i+1][j-1]
             * 所以i必须从下往上遍历，先算i+1行，再算i行
             * j的遍历顺序无所谓，因为i+1行的j都已经算完了
             * */
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    if (i == j)
                        dp[i][j] = true;
                    else
                        dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                    if (dp[i][j])
                        res++;
                }
            }
            return res;
        }
    }

    /*
     * 思路一：递归DP
     * 1. 定义函数dp(s,i,j)，表示s[i..j]是否为回文子串
     * 2. 状态转移：dp(s,i,j) = (s[i] == s[j]) && dp(s,i+1,j-1)
     * 3. 遍历所有(i,j)，统计dp(s,i,j)=true的个数
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * */
    class Solution1 {
        int[][] memo;

        public int countSubstrings(String s) {
            int n = s.length();
            memo = new int[n][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            int res = 0;
            for (int i = 0; i < n; i++)
                for (int j = i; j < n; j++)
                    if (dp(s, i, j) == 1)
                        res++;
            return res;
        }

        private int dp(String s, int i, int j) {
            if (i >= j)
                return 1;
            if (memo[i][j] != -1)
                return memo[i][j];
            if (s.charAt(i) == s.charAt(j))
                memo[i][j] = dp(s, i + 1, j - 1);
            else
                memo[i][j] = 0;
            return memo[i][j];
        }
    }


    public static void main(String[] args) {
        Solution solution = new PalindromicSubstrings().new Solution();
        // put your test code here

    }
}