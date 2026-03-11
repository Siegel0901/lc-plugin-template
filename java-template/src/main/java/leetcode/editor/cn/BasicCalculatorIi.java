package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class BasicCalculatorIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int calculate(String s) {
            Queue<Character> charQ = new ArrayDeque<>();
            for (char c : s.toCharArray())
                charQ.offer(c);
            return calculate(charQ);
        }

        int calculate(Queue<Character> charQ) {
            Deque<Integer> stk = new ArrayDeque<>();
            int num = 0;
            char sign = '+';
            while (!charQ.isEmpty()) {
                Character c = charQ.poll();
                if (c == ' ')
                    continue;
                else if (Character.isDigit(c))
                    num = num * 10 + (c - '0');
                else if (c == '(')
                    num = calculate(charQ);
                else if (c == ')')
                    break;
                else {
                    operator(sign, stk, num);
                    sign = c;
                    num = 0;
                }
            }
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


    public static void main(String[] args) {
        Solution solution = new BasicCalculatorIi().new Solution();
        // put your test code here

    }
}