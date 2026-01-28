package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class SubarrayProductLessThanK {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：滑动窗口
         * 1. 什么时候扩大窗口？当前窗口元素乘积小于k
         * 2. 什么时候缩小窗口？当前窗口元素乘积大于等于k
         * 3. 什么时候更新结果？当前窗口元素乘积小于k时，窗口数组的所有连续子数组都是合法结果
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @param k    目标和
         * @return 合法的子数组个数
         */
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int prod = 1;
            int left = 0, right = 0;
            int count = 0;
            while (right < nums.length) {
                prod *= nums[right++];
                while (left < right && prod >= k)
                    prod /= nums[left++];
                // 现在窗口内的元素的连续子数组都是合法数组
                // 如{1,2,3} -> {1,2,3}, {2,3}, {3}
                // 即[left,right), [left + 1,right), ..., [right - 1,right)，共(right - 1) - left + 1 = right - left个数组
                count += right - left;
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SubarrayProductLessThanK().new Solution();
        // put your test code here
        System.out.println(solution.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }
}