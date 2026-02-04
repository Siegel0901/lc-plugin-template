package leetcode.editor.cn;

import java.util.ArrayDeque;

public class EvaluateReversePolishNotation {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：操作数栈
         * 1. 遍历数组，遇到操作数入栈
         * 2. 遇到运算符，则弹出两次栈顶
         * 2.1. 第一次弹出的元素作为操作数b，第二次弹出的元素为被操作数a
         * 2.2. 执行运算：a op b = res
         * 2.3. 将res加入栈
         * 3. 遍历结束后，返回栈顶元素
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param tokens 表达式
         * @return 结果
         */
        public int evalRPN(String[] tokens) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            for (String token : tokens) {
                if ("+-*/".contains(token)) {
                    int b = stack.pop();
                    int a = stack.pop();
                    switch (token) {
                        case "+":
                            stack.push(a + b);
                            break;
                        case "-":
                            stack.push(a - b);
                            break;
                        case "*":
                            stack.push(a * b);
                            break;
                        case "/":
                            stack.push(a / b);
                            break;
                    }
                } else {
                    stack.push(Integer.parseInt(token));
                }
            }
            return stack.pop();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new EvaluateReversePolishNotation().new Solution();
        // put your test code here
        System.out.println(solution.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}