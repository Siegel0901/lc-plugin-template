package leetcode.editor.cn;

import java.util.Stack;

public class ValidParentheses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char leftOf(char c) {
            if (c == ')') return '(';
            if (c == ']') return '[';
            return '{';
        }

        public boolean isValid(String s) {
            Stack<Character> left = new Stack<>();
            for (Character c : s.toCharArray()) {
                if (c == '(' || c == '[' || c == '{') {
                    // 字符c是左括号,入栈
                    left.push(c);
                } else {
                    // 字符c是右括号
                    // 1. 判断栈是否为空
                    // 2. 判断最后一个入栈的元素是否与字符c匹配
                    if (!left.isEmpty() && leftOf(c) == left.peek())
                        left.pop();
                    else {
                        return false;
                    }
                }
            }
            // 最后查看栈内是否还有剩余的左括号
            return left.isEmpty();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        // put your test code here

    }
}