package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SquaresOfASortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：利用容器排序方法排序
         * 1. 将数组中的元素取平方构成新数组
         * 2. 对新数组排序
         * 时间复杂度：O(nlogn)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @return 结果数组
         */
//        public int[] sortedSquares(int[] nums) {
//            return Arrays.stream(nums).map(i -> i * i).sorted().toArray();
//        }

        /**
         * 思路二：双指针合并有序数组
         * 1. 将小于0的元素放到nums1中，将大于等于0的元素放到num2中
         * 2. 对nums1中的元素取绝对值
         * 3. 定义双指针指向nums1头和nums2尾（遍历升序数组）
         * 4. 对比指针元素大小，将大的放到nums的末尾
         * 5. 其中一个数组走完时，处理另一个数组的剩余元素至nums
         * 6. 对nums中的值取平方
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         * @return 结果数组
         */
//        public int[] sortedSquares(int[] nums) {
//            int[] nums1 = Arrays.stream(nums).filter(i -> i < 0).map(Math::abs).toArray();
//            int[] nums2 = Arrays.stream(nums).filter(i -> i >= 0).toArray();
//            int left = 0, right = nums2.length - 1, index = nums.length - 1;
//            while (left < nums1.length && right >= 0)
//                nums[index--] = nums1[left] > nums2[right] ? nums1[left++] : nums2[right--];
//            while (left < nums1.length)
//                nums[index--] = nums1[left++];
//            while (right >= 0)
//                nums[index--] = nums2[right--];
//            nums = Arrays.stream(nums).map(i -> i * i).toArray();
//            return nums;
//        }
        public int[] sortedSquares(int[] nums) {
            int left = 0, right = nums.length - 1, index = nums.length - 1;
            int[] res = new int[nums.length];
            while (left <= right)
                res[index--] = Math.abs(nums[left]) > Math.abs(nums[right]) ? nums[left] * nums[left++] : nums[right] * nums[right--];
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SquaresOfASortedArray().new Solution();
        // put your test code here
        int[] nums = {-4, -1, 0, 3, 10};
        solution.sortedSquares(nums);
    }
}