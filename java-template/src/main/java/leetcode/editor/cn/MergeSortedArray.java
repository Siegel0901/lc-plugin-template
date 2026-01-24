package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class MergeSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双指针
         * 1. 将num1中前m个元素放到大小为m的num3中
         * 2. 双指针指向num2,num3，比较元素大小，小的放入num1中
         * 3. 其中一个数组遍历完后，将另一个数组的剩余元素放入num1中
         * 时间复杂度：O(m + n)
         * 空间复杂度：O(m)
         *
         * @param nums1 数组1
         * @param m     数组1的长度
         * @param nums2 数组2
         * @param n     数组2的长度
         */
//        public void merge(int[] nums1, int m, int[] nums2, int n) {
//            int[] nums3 = Arrays.copyOf(nums1, m);
//            int i = 0, j = 0, index = 0;
//            while (i < nums2.length && j < nums3.length)
//                nums1[index++] = nums3[j] <= nums2[i] ? nums3[j++] : nums2[i++];
//            while (i < nums2.length)
//                nums1[index++] = nums2[i++];
//            while (j < nums3.length)
//                nums1[index++] = nums3[j++];
//        }

        /**
         * 思路二：双指针逆序遍历
         * 1. 双指针指向num1和num2元素的末尾，判断大小加入num1数组的末尾
         * 2. num1中的原有元素被覆盖时已经比较过大小了
         * 3. 当某个数组走到头时，只需要考虑复制num2的剩余元素
         * 时间复杂度：O(m + n)
         * 空间复杂度：O(1)
         *
         * @param nums1 数组1
         * @param m     数组1的长度
         * @param nums2 数组2
         * @param n     数组2的长度
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1, j = n - 1, index = m + n - 1;
            while (i >= 0 && j >= 0)
                nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
            // 仅考虑num2中是否有剩余元素
            while (j >= 0)
                nums1[index--] = nums2[j--];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        // put your test code here

    }
}