package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class RemoveElement {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双指针删除重复元素
         * 1. 将数组分为无val区和遍历区
         * 2. 初始化无val区索引index和遍历工作指针p为0
         * 3. 对比p的值和val的值
         * 4. 若不相等，则nums[index]的值为nums[p]，index后移
         * 5. p后移
         * 6. 当p == nums.length时，返回index（指向无val区的下一个元素位置）
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @param val  待删除的元素值
         * @return 删除元素后的数组长度
         */
        public int removeElement(int[] nums, int val) {
            if (nums.length == 0) return 0;
            if (val < 0 || val > 50) return nums.length;
            int index = 0;
            for (int p = 0; p < nums.length; p++) {
                if (nums[p] != val)
                    nums[index++] = nums[p];
            }
            return index;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveElement().new Solution();
        // put your test code here

    }
}