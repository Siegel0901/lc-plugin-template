package leetcode.editor.cn;

import java.util.ArrayDeque;

public class NextGreaterElementIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调栈 + 循环数组技巧模拟双倍数组长度
         * 1. 将数组逻辑复制一份，长度加倍，利用单调栈求解
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         * @return 结果数组
         */
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            int[] res = new int[n];
            for (int i = 2 * n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && stk.peek() <= nums[i % n])
                    stk.pop();
                res[i % n] = stk.isEmpty() ? -1 : stk.peek();
                stk.push(nums[i % n]);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NextGreaterElementIi().new Solution();
        // put your test code here

    }
}