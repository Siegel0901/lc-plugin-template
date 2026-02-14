package leetcode.editor.cn;

import java.util.ArrayDeque;

public class ShortestUnsortedContinuousSubarray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调递增栈 + 单调递减栈
         * 1. 正向便利，维护单调递增栈，弹出元素的最小索引为左边界
         * 2. 逆向遍历，维护单调递减栈，弹出元素的最大索引为右边界
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         * @return 结果
         */
        public int findUnsortedSubarray(int[] nums) {
            int n = nums.length;
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                while (!stk.isEmpty() && nums[stk.peek()] > nums[i])
                    left = Math.min(left, stk.pop());
                stk.push(i);
            }
            stk.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && nums[stk.peek()] < nums[i])
                    right = Math.max(right, stk.pop());
                stk.push(i);
            }
            if (left == Integer.MAX_VALUE && right == Integer.MIN_VALUE)
                return 0;
            return right - left + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ShortestUnsortedContinuousSubarray().new Solution();
        // put your test code here
        System.out.println(solution.findUnsortedSubarray(new int[]{1, 2, 3, 3, 3}));

    }
}