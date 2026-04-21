package leetcode.editor.cn;

public class NextPermutation {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：当作数字比大小
     * 1. 从右往左找第一个值下降的位置：nums[i] < nums[i+1]，i即为下降点
     * 2. 从右往左找第一个比nums[i]大的数：在i的右边找nums[j]，使得nums[i] < nums[j]
     * 3. 交换：交换nums[i]和nums[j]
     * 4. 反转：[i,n-1]是升序，j是第一个比i大的数，交换后的[i,i+1,j,n-1]仍为升序，反转[i+1,n-1]使其变为降序
     * 5. 若[0,n-1]本来就是降序，说明是最大排列，直接反转为最小排列
     * 举例：
     * 以 nums = [1,3,2] 为例：
     * 找下降点：从右往左，3 > 2（继续），1 < 3（找到！i=0）
     * 找交换位置：从右往左找第一个比 1 大的数，2 > 1（j=2）
     * 交换：[1,3,2] → [2,3,1]
     * 反转 i+1 到末尾：反转 [3,1] → [1,3]
     * 结果：[2,1,3] ✓
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public void nextPermutation(int[] nums) {
            int n = nums.length;

            // 步骤1: 从右往左找第一个下降点 nums[i] < nums[i+1]
            int i = n - 2;
            // 严格小于必须加等号跳过两数相等的情况
            while (i >= 0 && nums[i] >= nums[i + 1]) i--;

            // 步骤2和3: 如果找到了下降点，从右往左找第一个比 nums[i] 大的数并交换
            if (i >= 0) {
                int j = n - 1;
                // 严格大于必须加等号跳过两数相等的情况
                while (j > i && nums[j] <= nums[i]) j--;
                swap(nums, i, j);
            }

            // 步骤4: 反转 i+1 到末尾的部分，使其变为升序（最小排列）
            reverse(nums, i + 1, n - 1);
        }

        void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        void reverse(int[] nums, int left, int right) {
            while (left < right)
                swap(nums, left++, right--);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        // put your test code here

    }
}