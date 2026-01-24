package leetcode.editor.cn;

public class RemoveDuplicatesFromSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双指针
         * 1. 定义指针i和j，初始值为0
         * 2. 将数组分为两个区域，一个无重复区，一个遍历区
         * 3. i为排序区的最后一个元素，j为遍历区的第一个元素
         * 4. 对比nums[i]和nums[j]
         * 5. 若nums[i]和nums[j]不相等，i后移，nums[i] = nums[j]
         * 6. j后移
         * 7. 当j == nums.length时，返回i + 1
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @return 去重后的数组长度
         */
        public int removeDuplicates(int[] nums) {
            // 题目保证length >= 1，非必需
            if (nums.length == 0)
                return 0;
            // index为无重复区索引
            int index = 0;
            // 遍历数组
            for (int j = 0; j < nums.length; j++)
                // 判断是否有新元素要加入无重复区
                if (nums[index] != nums[j])
                    // 先后移再赋值
                    nums[++index] = nums[j];
            // index + 1为无重复区元素个数
            return index + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedArray().new Solution();
        // put your test code here
        solution.removeDuplicates(new int[]{1, 1, 2});
    }
}