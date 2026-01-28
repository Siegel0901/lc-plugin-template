package leetcode.editor.cn;

public class MinimumSizeSubarraySum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：滑动窗口
         * 1. 什么时候扩大窗口？窗口内元素总和小于target
         * 2. 什么时候缩小窗口？窗口内元素总和大于等于target
         * 3. 什么时候返回结果？窗口内元素总和大于等于target，且窗口最小
         * 时间复杂度：
         * 空间复杂度：
         *
         * @param target 目标
         * @param nums   数组
         * @return 最小子数组长度
         */
        public int minSubArrayLen(int target, int[] nums) {
            int windowSum = 0;
            int left = 0, right = 0;
            int min = Integer.MAX_VALUE;
            while (right < nums.length) {
                windowSum += nums[right++];
                while (left < right && windowSum >= target) {
                    int l = nums[left];
                    if (windowSum - l < target)
                        min = Math.min(min, right - left);
                    windowSum -= l;
                    left++;
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumSizeSubarraySum().new Solution();
        // put your test code here
        System.out.println(solution.minSubArrayLen(4, new int[]{2, 3, 1, 2, 4, 3}));
    }
}