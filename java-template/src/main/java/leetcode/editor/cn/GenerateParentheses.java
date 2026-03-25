package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int n;

        /**
         * 思路三：按照无重复可重选排列问题求解
         */
        public List<String> generateParenthesis(int n) {
            this.n = n;
            backtrack(0);
            return res;
        }

        void backtrack(int start) {
            if (sb.length() == 2 * n && isValid(sb.toString())) {
                res.add(sb.toString());
                return;
            }
            if (sb.length() > 2 * n)
                return;
            String[] bracket = {"(", ")"};
            for (int i = 0; i < bracket.length; i++) {
                sb.append(bracket[i]);
                backtrack(i);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        boolean isValid(String s) {
            int left = 0;
            int right = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') left++;
                else right++;
                if (right > left)
                    return false;
            }
            return left == right;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution2 {
        List<String> res = new ArrayList<>();

        /**
         * 思路二：回溯 + 剪枝
         */
        public List<String> generateParenthesis(int n) {
            StringBuilder sb = new StringBuilder();
            backtrack(n, n, sb);
            return res;
        }

        void backtrack(int left, int right, StringBuilder sb) {
            if (right < left)
                return;
            if (right < 0 || left < 0)
                return;
            if (left == 0 && right == 0) {
                res.add(sb.toString());
                return;
            }
            sb.append('(');
            backtrack(left - 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);

            sb.append(')');
            backtrack(left, right - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    class Solution1 {
        List<String> res = new ArrayList<>();

        /**
         * 合法括号组合性质：
         * 1. 左括号数量等于右括号数量
         * 2. 从左向右遍历的过程中，左括号数量要大于等于右括号的数量
         * 思路：回溯
         * 1. 利用回溯算法穷举2n个位置'('和')'的组合
         * 2. 判断括号组合的合法性，收集合法括号组合
         */
        public List<String> generateParenthesis(int n) {
            StringBuilder sb = new StringBuilder();
            backtrack(n, sb);
            return res;
        }

        void backtrack(int n, StringBuilder sb) {
            if (sb.length() == 2 * n) {
                String s = sb.toString();
                if (isValid(s))
                    res.add(s);
                return;
            }
            sb.append('(');
            backtrack(n, sb);
            sb.deleteCharAt(sb.length() - 1);
            sb.append(')');
            backtrack(n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        boolean isValid(String s) {
            int left = 0;
            int right = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') left++;
                else right++;
                if (right > left)
                    return false;
            }
            return left == right;
        }
    }


    public static void main(String[] args) {
        Solution solution = new GenerateParentheses().new Solution();
        // put your test code here
        System.out.println(solution.generateParenthesis(4));
    }
}