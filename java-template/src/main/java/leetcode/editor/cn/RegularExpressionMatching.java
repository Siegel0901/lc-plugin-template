package leetcode.editor.cn;

import java.util.Arrays;

public class RegularExpressionMatching {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路三：DP Table + 空间压缩
     * 时间复杂度：O(MN)
     * 空间复杂度：O(N)
     * */
    class Solution {
        public boolean isMatch(String s, String p) {
            int m = s.length(), n = p.length();
            boolean[] dp = new boolean[n + 1];

            // base case: 两个字符串都为空时匹配
            dp[n] = true;

            // base case：s为空，处理p末尾的x*
            for (int j = n - 2; j >= 0; j -= 2) {
                if (p.charAt(j + 1) == '*')
                    dp[j] = dp[j + 2];
                else
                    break;
            }

            /*
             * 状态转移：
             * 1. 当前字符匹配：
             * 1.1. 下一个字符为*：
             * 1.1.1. 匹配0次：dp[i][j] = dp[i][j+2]
             * 1.1.2. 匹配多次：dp[i][j] = dp[i+1][j]
             * 1.2. 下一个字符不为*：dp[i][j] = dp[i+1][j+1]
             * 2. 当前字符不匹配：
             * 2.1. 下一个字符为*，匹配0次：dp[i][j] = dp[i][j+2]
             * 2.2. 下一个字符不为*：dp[i][j] = false
             * i/j      j-1                     j                           j+1                         j+2
             * i        dp[i][j-1]【dp[j]】      dp[i][j](dp[j])             dp[i][j+1]【dp[j+2]】      dp[i][j+2](dp[j+2])
             * i+1      dp[i+1][j-1]【temp】     dp[i+1][j](temp)【prev】     dp[i+1][j+1](prev)
             * 遍历顺序：从下往上，从右往左
             * */
            for (int i = m - 1; i >= 0; i--) {
                // prev = dp[i+1][j+1],j+1=n
                boolean prev = dp[n];
                // i<m,dp[i][n] = false(s非空，p为空，无法匹配)
                dp[n] = false;
                for (int j = n - 1; j >= 0; j--) {
                    // temp = dp[i+1][j]
                    boolean temp = dp[j];
                    boolean firstMatch = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                    boolean nextIsStar = j < n - 1 && p.charAt(j + 1) == '*';
                    if (nextIsStar)
                        // 有*通配符：匹配0次或多次
                        // 匹配0次：跳过p[j]和*，即dp[i][j+2]
                        // 匹配多次：如果首字符匹配，s往后移一位，p不动，即firstMatch && dp[i+1][j]
                        dp[j] = dp[j + 2] || (firstMatch && temp);
                    else
                        // 无*通配符：只能匹配一次
                        dp[j] = firstMatch && prev;
                    /*
                     * prev = temp = dp[i+1][j]
                     * 对于j-1来说,prev = dp[i+1][j-1+1]
                     * prev始终为dp[i+1][j+1]
                     * */
                    prev = temp;
                }
            }
            return dp[0];
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路二：DP Table
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     * */
    class Solution2 {
        public boolean isMatch(String s, String p) {
            int m = s.length(), n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];

            // base case: 两个字符串都为空时匹配
            dp[m][n] = true;

            /*
             * base case：处理s为空，p不为空的情况
             * 从右往左扫描p，消掉x*组合，遇到消不掉的普通字符为止
             * */
            for (int j = n - 2; j >= 0; j -= 2) {
                if (p.charAt(j + 1) == '*')
                    dp[m][j] = dp[m][j + 2];
                else
                    break;
            }

            /*
             * 状态转移：
             * 1. 当前字符匹配：
             * 1.1. 下一个字符为*：
             * 1.1.1. 匹配0次：dp[i][j] = dp[i][j+2]
             * 1.1.2. 匹配多次：dp[i][j] = dp[i+1][j]
             * 1.2. 下一个字符不为*：dp[i][j] = dp[i+1][j+1]
             * 2. 当前字符不匹配：
             * 2.1. 下一个字符为*，匹配0次：dp[i][j] = dp[i][j+2]
             * 2.2. 下一个字符不为*：dp[i][j] = false
             * */
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    boolean firstMatch = (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                    boolean nextIsStar = j < n - 1 && p.charAt(j + 1) == '*';
//                    if (firstMatch) {
//                        if (nextIsStar)
//                            dp[i][j] = dp[i][j + 2] || dp[i + 1][j];
//                        else
//                            dp[i][j] = dp[i + 1][j + 1];
//                    } else {
//                        if (nextIsStar)
//                            dp[i][j] = dp[i][j + 2];
//                        else
//                            dp[i][j] = false;
//                    }
                    if (nextIsStar)
                        // 有*通配符：匹配0次或多次
                        // 匹配0次：跳过p[j]和*，即dp[i][j+2]
                        // 匹配多次：如果首字符匹配，s往后移一位，p不动，即firstMatch && dp[i+1][j]
                        dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                    else
                        // 无*通配符：只能匹配一次
                        dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
            return dp[0][0];
        }
    }

    /*
    * 思路一：递归DP
    * 1. 如果没有*的解法：
            int i = 0, j = 0;
            while (i < s.length() && j < p.length()) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
            return i == j;
    * 2. 若p[j+1]=='*'：
    * 2.1. 若s[i] == p[j]:
    * 2.1.1. p[j]有可能匹配多个s[i]，如s="aaa",p="a*"
    * 2.1.2. p[j]有可能匹配0个s[i]，如s="aa",p="a*aa"
    * 2.2. 若s[i] != p[j]，只有一种情况：p[j]只能匹配0次，如s="aa",p="c*aa"
    * 2.3. 解法改造：
            int i = 0, j = 0;
            while (i < s.length() && j < p.length()) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    if (j < p.length() - 1 && p.charAt(j+1) == '*') {
                        // 有*通配符，可以匹配0次或多次
                    } else {
                        // 无*通配符，匹配一次
                        i++;
                        j++;
                    }
                } else {
                    if (j < p.length() - 1 && p.charAt(j+1) == '*') {
                        // 有*通配符，只能匹配0次
                    } else {
                        // 无*通配符，无法匹配
                        return false;
                    }
                }
            }
            return i == j;
    * 时间复杂度：O(MN)
    * 空间复杂度：O(MN)
    * */
    class Solution1 {
        int[][] memo;

        public boolean isMatch(String s, String p) {
            int m = s.length(), n = p.length();
            memo = new int[m][n];
            for (int[] row : memo)
                Arrays.fill(row, -1);
            return dp(s, 0, p, 0);
        }

        /**
         * 定义:dp(s,i,p,j)表示s[i..]能否与p[j..]匹配
         * 1. 当前字符s[i]和p[j]可以匹配：
         * 1.1. 下一个字符不是*通配符，只能匹配一次，i和j都往后移一位：dp(s,i,p,j) = dp(s,i+1,p,j+1)
         * 1.2. 下一个字符为*通配符:
         * 1.2.1. 匹配0次，i不动，j往后移两位（跳过*通配符）：dp(s,i,p,j) = dp(s,i,p,j+2)
         * 1.2.2. 匹配多次，i往后移一位（匹配*），j不动：dp(s,i,p,j) = dp(s,i+1,p,j)
         * 2. 当前字符s[i]和p[j]不可以匹配：
         * 2.1. 下一个字符不是*通配符：dp(s,i,p,j) = false
         * 2.2. 下一个字符为*通配符，只能匹配0次，i不动，j往后移两位（跳过*通配符）：dp(s,i,p,j) = dp(s,i,p,j+2)
         */
        boolean dp(String s, int i, String p, int j) {
            // base case1：p遍历完了，看s是否也遍历完
            if (j == p.length())
                return i == s.length();
            // base case2:s遍历完了
            if (i == s.length()) {
                /*
                 * 如果p剩余奇数个字符,则至少有一个字符未匹配,如a*b
                 * 只有剩余偶数字符,且与*号成对出现才能匹配空字符,如a*b*c*
                 * */
                if (((p.length() - j) & 1) == 1)
                    return false;
                // 遍历剩余字符的偶数位置,检查是否为*
                for (; j + 1 < p.length(); j += 2)
                    if (p.charAt(j + 1) != '*')
                        return false;
                return true;
            }
            // 查备忘录
            if (memo[i][j] != -1)
                return memo[i][j] == 1;
            // 检查下一个字符是否为*通配符
            boolean nextIsStar = j < p.length() - 1 && p.charAt(j + 1) == '*';
            // 当前字符可以匹配
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                if (nextIsStar)
                    // 有*通配符，可以匹配0次或多次
                    memo[i][j] = (dp(s, i, p, j + 2) || dp(s, i + 1, p, j)) ? 1 : 0;
                else
                    // 无*通配符，匹配一次
                    memo[i][j] = dp(s, i + 1, p, j + 1) ? 1 : 0;
            } else {
                if (nextIsStar)
                    // 有*通配符，只能匹配0次
                    memo[i][j] = dp(s, i, p, j + 2) ? 1 : 0;
                else
                    // 无*通配符，无法匹配
                    memo[i][j] = 0;
            }
            return memo[i][j] == 1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching().new Solution();
        // put your test code here
        solution.isMatch("aab", "c*a*b");
    }
}