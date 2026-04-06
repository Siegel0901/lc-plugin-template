package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestValidParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            Deque<Integer> stk = new ArrayDeque<>();
            // dp[i]记录以s[i-1]结尾的最长合法括号子串的长度
            int[] dp = new int[s.length() + 1];
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    // 遇到左括号，记录索引
                    stk.push(i);
                    // 左括号不可能是合法括号子串的结尾
                    dp[i + 1] = 0;
                } else {
                    // 遇到右括号
                    if (!stk.isEmpty()) {
                        // 配对的左括号索引
                        int leftIdx = stk.pop();
                        // 以这个右括号结尾的最长子串长度
                        dp[i + 1] = i - leftIdx + 1 + dp[leftIdx];
                    } else {
                        // 没有配对的左括号
                        dp[i + 1] = 0;
                    }
                }
            }
            int res = 0;
            for (int val : dp)
                res = Math.max(res, val);
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestValidParentheses().new Solution();
        // put your test code here

    }
}