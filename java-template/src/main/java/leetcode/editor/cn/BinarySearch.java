package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class BinarySearch {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：二分查找
         * 时间复杂度：O(logn)
         * 空间复杂度：O(1)
         *
         * @param nums   数组
         * @param target 目标值
         * @return 目标值在数组中的索引，不存在则返回-1
         */
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target)
                    return mid;
                else if (nums[mid] < target)
                    left = mid + 1;
                else if (target < nums[mid])
                    right = mid;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new BinarySearch().new Solution();
        // put your test code here

    }
}