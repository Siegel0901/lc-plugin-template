package leetcode.editor.cn;

import java.util.ArrayDeque;

public class RemoveKDigits {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调栈寻找上一个更小或相等的元素
         * 1. 上一个：正序遍历
         * 2. 更小或相等的元素：弹出大于当前元素的栈顶元素
         * 3. 删去K个数：弹出的元素即为删去的元素
         * 4. 若遍历完后，仍需删除（此时栈底到栈顶为升序），则接着弹出栈顶逆序删除（先删除大的）
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param num 数字
         * @param k   删除的位数
         * @return 最小数字
         */
        public String removeKdigits(String num, int k) {
            ArrayDeque<Character> stk = new ArrayDeque<>();
            for (char c : num.toCharArray()) {
                while (!stk.isEmpty() && stk.peek() > c && k > 0) {
                    stk.pop();
                    k--;
                }
                // 剔除前导零
                if (stk.isEmpty() && c == '0')
                    continue;
                stk.push(c);
            }
            // 若k>0，此时stk中的元素为升序
            while (k > 0 && !stk.isEmpty()) {
                stk.pop();
                k--;
            }
            // 转化结果
            StringBuilder sb = new StringBuilder();
            while (!stk.isEmpty())
                sb.append(stk.pop());
            return sb.isEmpty() ? "0" : sb.reverse().toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveKDigits().new Solution();
        // put your test code here
        System.out.println(solution.removeKdigits("52660469", 2));
    }
}