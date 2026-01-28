package leetcode.editor.cn;

import java.util.Arrays;

public class MinimumOperationsToReduceXToZero {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双倍数组滑动窗口
         * 1. 将数组扩展为原来的两倍，复制原来的数组元素到末尾
         * 2. 使用滑动窗口找到和为x的元素序列
         * 3. 元素序列合法判断：
         * 3.1. 元素序列长度right - left <= nums.length
         * 3.2. [i, j] i == 0, j < nums.length
         * 3.3. [i, j] i < nums.length, j == nums.length - 1
         * 3.4. [i, j] i < nums.length, nums.length <= j < 2 * nums.length
         * 4. 当left>=nums.length时，说明窗口已经完全移动到复制数组区域，程序没有必要继续了，停止循环
         * 5. 什么时候扩大窗口？当前元素序列和小于x
         * 6. 什么时候缩小窗口？当前元素序列和大于等于x
         * 7. 什么时候更新结果？当前元素序列和等于x
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         * @param x    目标和
         * @return 最小操作数
         */
//        public int minOperations(int[] nums, int x) {
//            // 复制数组
//            int[] doubleNums = new int[nums.length * 2];
//            System.arraycopy(nums, 0, doubleNums, 0, nums.length);
//            System.arraycopy(nums, 0, doubleNums, nums.length, nums.length);
//
//            int left = 0, right = 0;
//            int min = Integer.MAX_VALUE, sum = 0;
//            // 当left >= nums.length，窗口完全移动至复制数组区域，停止循环
//            while (left < nums.length && right < doubleNums.length) {
//                // 扩大窗口，并更新sum
//                sum += doubleNums[right++];
//                // 判断sum是否超过或等于x
//                while (sum >= x) {
//                    // sum == x，且索引合法
//                    if (sum == x && isValidIndex(nums.length, left, right - 1))
//                        // 更新结果
//                        min = Math.min(min, right - left);
//                    // 缩小窗口，并更新sum
//                    sum -= doubleNums[left++];
//                }
//            }
//            return min == Integer.MAX_VALUE ? -1 : min;
//        }
//
//        // 判断双倍数组中的索引是否合法
//        public boolean isValidIndex(int len, int start, int end) {
//            // 元素序列长度判断
//            if (end - start + 1 > len) return false;
//            // 前缀：从0开始，且在原数组内
//            if (start == 0) return true;
//            // 后缀：结束于原数组末尾
//            if (end == len - 1) return true;
//            // 跨段：从原数组某处开始，延伸到复制部分
//            return end >= len;
//        }

        /**
         * 思路二：滑动窗口求和为totalSum - x的最长连续子序列
         * 1. 求和为totalSum - x的最长连续子序列
         * 2. 最小操作数为：nums.length - 和为totalSum - x的最长连续子序列的长度
         * 3. 什么时候扩展窗口？当前子序列和小于totalSum - x
         * 4. 什么时候缩小窗口？当前子序列和大于等于totalSum - x
         * 5. 什么时候更新结果？当前子序列和等于totalSum - x
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param nums 数组
         * @param x    目标和
         * @return 最小操作数
         */
        public int minOperations(int[] nums, int x) {
            // 求和为totalSum - x
            x = Arrays.stream(nums).sum() - x;
            // 边界条件
            if (x < 0) return -1;
            if (x == 0) return nums.length;

            int left = 0, right = 0;
            int max = -1, sum = 0;
            while (right < nums.length) {
                // 扩大窗口，并更新sum
                sum += nums[right++];
                // 判断sum是否超过或等于x
                while (sum >= x) {
                    if (sum == x)
                        // 更新结果
                        max = Math.max(max, right - left);
                    // 缩小窗口，并更新sum
                    sum -= nums[left++];
                }
            }
            return max == -1 ? -1 : nums.length - max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumOperationsToReduceXToZero().new Solution();
        // put your test code here
        solution.minOperations(new int[]{1, 1, 4, 2, 3}, 5);
    }
}