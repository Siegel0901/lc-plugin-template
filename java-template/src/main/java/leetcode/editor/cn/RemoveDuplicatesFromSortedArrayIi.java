package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class RemoveDuplicatesFromSortedArrayIi {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双指针遍历有序数组去重
         * 1. 将数组分为目标区和遍历区，目标区存放的元素个数小于等于2
         * 2. 初始化目标区指针index为0，和遍历工作指针p为1
         * 3. 遍历有序数组
         * 4. 对比nums[index]和nums[p]
         * 4.1. 若相同，则判断当前元素出现个数count是否为2，不为2，则index后移，p值赋值给index处，count++
         * 4.1.2 若为2，则p后移
         * 4.2 若不相同，则index后移，p值赋值给index处，count置为1
         * 5. 遍历结束后，返回index + 1
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @return 数组长度
         */
        public int removeDuplicates(int[] nums) {
            // 题目保证nums.length >= 1
            int index = 0, count = 1;
            for (int p = 1; p < nums.length; p++) {
                // 遇到相同元素且计数小于2
                if (nums[index] == nums[p] && count < 2) {
                    nums[++index] = nums[p];
                    count++;
                } else if (nums[index] != nums[p]) {    // 遇到不同元素
                    nums[++index] = nums[p];
                    count = 1;
                }
            }
            return index + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedArrayIi().new Solution();
        // put your test code here

    }
}