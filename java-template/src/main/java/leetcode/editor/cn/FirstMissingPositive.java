package leetcode.editor.cn;

public class FirstMissingPositive {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：原地哈希(置换法)
     * 1. 数组长度为n，则答案一定在[1,n+1]范围内
     * 1.1. 若数组为[1,2,3,...,n]，则答案为n+1
     * 1.2. 若数组缺某个正整数，答案在[1,n]之间
     * 2. 如何原地哈希？
     * 2.1. 让数字i出现在索引i-1的位置上(即nums[i] = i+1)
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            // 第一步：将每个正整数放到它应该在的位置
            for (int i = 0; i < n; i++) {
                /*
                 * 当nums[i]在[1,n]范围内，且不在正确位置时，进行交换
                 * 1. 为什么使用while而不是if？
                 * 因为交换后，新的nums[i]也可能需要归位
                 * 2. 为什么要判断nums[nums[i] - 1] != nums[i]
                 * 因为当nums[nums[i] - 1] == nums[i]时（两个数相同），会造成死循环
                 * */
                while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i])
                    // 将num[i]放到索引num[i]-1的位置
                    swap(nums, i, nums[i] - 1);
            }
            // 第二步：找到第一个不在正确位置的数
            for (int i = 0; i < n; i++)
                if (nums[i] != i + 1)
                    return i + 1;
            // 如果[1,n]都在，返回n+1
            return n + 1;
        }

        void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new FirstMissingPositive().new Solution();
        // put your test code here
        solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12});
    }
}