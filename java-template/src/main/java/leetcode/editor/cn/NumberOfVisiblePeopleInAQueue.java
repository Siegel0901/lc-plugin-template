package leetcode.editor.cn;

import java.util.ArrayDeque;

public class NumberOfVisiblePeopleInAQueue {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调栈
         * 1. 维护单调栈的过程中，记录弹出的元素个数即为下一个更大或相等元素和当前元素之间的人数
         * 2. 若单调栈非空，则说明找到了下一个更大或相等元素，计数+1
         * 3. 记录计数结果
         * 4. 将当前元素加入栈中，继续遍历
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param heights 高度数组
         * @return 结果
         */
        public int[] canSeePersonsCount(int[] heights) {
            int n = heights.length;
            int[] res = new int[n];
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                int count = 0;
                while (!stk.isEmpty() && stk.peek() < heights[i]) {
                    count++;
                    stk.pop();
                }
                res[i] = stk.isEmpty() ? count : count + 1;
                stk.push(heights[i]);
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NumberOfVisiblePeopleInAQueue().new Solution();
        // put your test code here

    }
}