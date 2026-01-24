package leetcode.editor.cn;

public class TwoSumIiInputArrayIsSorted {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路一：双循环遍历求和
         * 1. 定义i和j，初始值为0
         * 2. 先固定i，遍历j，对i和j求和
         * 3. 若求和值为target，则返回{i + 1, j + 1}
         * 4. i后移
         * 时间复杂度：O(n^2)【Time Limit Exceeded】
         * 空间复杂度：O(1)
         *
         * @param numbers 数组
         * @param target  目标值
         * @return 索引
         */
//        public int[] twoSum(int[] numbers, int target) {
//            for (int i = 0; i < numbers.length; i++)
//                for (int j = i + 1; j < numbers.length; j++)
//                    if (numbers[i] + numbers[j] == target)
//                        return new int[]{i + 1, j + 1};
//            return new int[]{};
//        }

        /**
         * 思路二：双指针类二分查找定位
         * 1. 思路一并没有利用到数组有序的特点
         * 2. 在遍历数组对left和right位置求和时，判断当前和sum与target的大小
         * 3. 若sum小于target，则left++，让sum增大
         * 4. 若target小于sum，则right--，让sum减小
         * 5. 若target等于sum，则返回{left + 1, right + 1}
         * 时间复杂度：O(n)【最坏情况left和right从两端移动到中间】
         * 空间复杂度：O(1)
         */
        public int[] twoSum(int[] numbers, int target) {
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum < target)
                    left++;
                else if (target < sum) {
                    right--;
                } else {
                    return new int[]{left + 1, right + 1};
                }
            }
            return new int[]{};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new TwoSumIiInputArrayIsSorted().new Solution();
        // put your test code here

    }
}