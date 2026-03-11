package leetcode.editor.cn;

import java.util.*;

public class BasicCalculator {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int calculate(String s) {
            Queue<Character> charQ = new ArrayDeque<>();
            for (char c : s.toCharArray())
                charQ.offer(c);
            return calculate(charQ);
        }

        int calculate(Queue<Character> charQ) {
            // 存放表达式中的值
            Deque<Integer> stk = new ArrayDeque<>();
            // 初始值为0
            int num = 0;
            // 第一个数字的符号设为+
            char sign = '+';
            // 遍历字符串
            while (!charQ.isEmpty()) {
                Character c = charQ.poll();
                if (c == ' ')   // 遇到空格跳过
                    continue;
                else if (Character.isDigit(c))  // 遇到数字字符则累加
                    num = num * 10 + (c - '0');
                else if (c == '(')  // 遇到左括号时,递归计算括号表达式的值
                    num = calculate(charQ);
                else if (c == ')')  // 遇到右括号,则退出循环计算并返回括号表达式的值
                    break;
                else {  // 遇到操作符,组合操作符和数字入栈
                    operator(sign, stk, num);
                    // 操作完成后,更新操作符和数值
                    sign = c;
                    num = 0;
                }
            }
            // 循环结束后,最后一个操作符和数值并未入栈,需入栈
            operator(sign, stk, num);
            return stkSum(stk);
        }

        void operator(char sign, Deque<Integer> stk, int num) {
            int prev;
            switch (sign) {
                case '+':
                    stk.push(num);
                    break;
                case '-':
                    stk.push(-num);
                    break;
                case '*':
                    prev = stk.pop();
                    stk.push(prev * num);
                    break;
                case '/':
                    prev = stk.pop();
                    stk.push(prev / num);
                    break;
            }
        }

        int stkSum(Deque<Integer> stk) {
            int res = 0;
            while (!stk.isEmpty())
                res += stk.pop();
            return res;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路：逐个功能拆解
     * 1. 加减
     * 2. 乘除
     * 3. 括号
     */
    class Solution1 {

        public int calculate(String s) {
            Map<Integer, Integer> bracket = new HashMap<>();
            Deque<Integer> leftBracket = new ArrayDeque<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(')
                    leftBracket.push(i);
                else if (c == ')')
                    bracket.put(leftBracket.pop(), i);
            }
            return calculateRange(s, 0, s.length() - 1, bracket);
        }

        int calculateRange(String s, int start, int end, Map<Integer, Integer> bracket) {
            Deque<Integer> nums = new ArrayDeque<>();
            char sign = '+';
            int num = 0;
            for (int i = start; i <= end; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c))
                    num = num * 10 + (c - '0');
                if (c == '(') {
                    num = calculateRange(s, i + 1, bracket.get(i) - 1, bracket);
                    i = bracket.get(i);
                }
                if (c == '+' || c == '-' || c == '*' || c == '/' || i == end) {
                    int prev;
                    switch (sign) {
                        case '+':
                            nums.push(num);
                            break;
                        case '-':
                            nums.push(-num);
                            break;
                        case '*':
                            prev = nums.pop();
                            nums.push(prev * num);
                            break;
                        case '/':
                            prev = nums.pop();
                            nums.push(prev / num);
                            break;
                    }
                    sign = c;
                    num = 0;
                }
            }
            int res = 0;
            while (!nums.isEmpty())
                res += nums.pop();
            return res;
        }

    }


    public static void main(String[] args) {
        Solution solution = new BasicCalculator().new Solution();
        // put your test code here

    }
}