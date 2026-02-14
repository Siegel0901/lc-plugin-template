package leetcode.editor.cn;

import java.util.ArrayDeque;

public class FinalPricesWithASpecialDiscountInAShop {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：单调栈寻找下一个更小或相等的元素
         * 1. 下一个：逆序遍历
         * 2. 更小或相等：弹出栈中大于当前元素的元素
         * 时间复杂度：O(n)
         * 空间复杂度；O(n)
         *
         * @param prices 价格数组
         * @return 结果数组
         */
        public int[] finalPrices(int[] prices) {
            int n = prices.length;
            int[] res = new int[n];
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && stk.peek() > prices[i])
                    stk.pop();
                res[i] = stk.isEmpty() ? prices[i] : prices[i] - stk.peek();
                stk.push(prices[i]);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FinalPricesWithASpecialDiscountInAShop().new Solution();
        // put your test code here

    }
}