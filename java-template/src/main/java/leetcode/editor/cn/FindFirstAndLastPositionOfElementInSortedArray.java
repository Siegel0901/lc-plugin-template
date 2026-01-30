package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class FindFirstAndLastPositionOfElementInSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：使用二分查找寻找左边界与右边界
         * 时间复杂度：O(logn)
         * 空间复杂度：O(1)
         *
         * @param nums   数组
         * @param target 目标值
         * @return [left, right]
         */
        public int[] searchRange(int[] nums, int target) {
            int left = left_bound(nums, target);
            int right = right_bound(nums, target);
            return new int[]{left, right};
        }

        public int left_bound(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (target <= nums[mid])
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left >= nums.length)
                return -1;
            return nums[left] == target ? left : -1;
        }

        public int right_bound(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                // 寻找第一个大于target的值
                if (nums[mid] > target)
                    right = mid;
                else
                    left = mid + 1;
            }
            // 夹出的位置 - 1即是最后一个target值
            if (left - 1 < 0 || left - 1 >= nums.length)
                return -1;
            return nums[left - 1] == target ? left - 1 : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // put your test code here

    }
}