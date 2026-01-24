package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class MoveZeroes {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双指针删除0元素，数组剩余长度赋值为0
         * 1. 将数组分为无0区和遍历区
         * 2. 初始化无0区索引index == 0和遍历区工作指针p == 0
         * 3. 若nums[p]不为0,则nums[index]赋值为nums[p]，index后移
         * 4. p后移
         * 5. 当p == nums.length时，从index开始遍历数组
         * 6. 将剩余元素全部赋值为0
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         */
        public void moveZeroes(int[] nums) {
            int index = 0;
            for (int p = 0; p < nums.length; p++) {
                if (nums[p] != 0)
                    nums[index++] = nums[p];
            }
            while (index < nums.length)
                nums[index++] = 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        // put your test code here

    }
}