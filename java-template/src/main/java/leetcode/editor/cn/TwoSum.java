package leetcode.editor.cn;

import java.util.Arrays;

public class TwoSum {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：排序 + 双指针
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // 复制原始数组用于排序
            int[] sorted = nums.clone();
            // 升序排序
            Arrays.sort(sorted);
            int n = nums.length;
            // 左右指针
            int l = 0, r = n - 1;
            // 遍历排序数组
            while (l < r) {
                // 左右指针指向的元素
                int left = sorted[l], right = sorted[r];
                // 双指针之和
                int sum = left + right;
                if (sum == target)
                    // 若等于目标,则找到了两个目标元素
                    break;
                else if (sum < target)
                    // 小于目标则l++,让sum增大,直到sorted[l]不等于left
                    while (l < r && sorted[l] == left)
                        l++;
                else
                    // 大于目标则r--,让sum减小,直到sorted[r]不等于right
                    while (l < r && sorted[r] == right)
                        r--;
            }
            // lIdx和rIdx表示两个目标元素在nums中的索引
            int lIdx = -1, rIdx = -1;
            for (int i = 0; i < n; i++) {
                // 找到了就不用就继续找了
                if (lIdx != -1 && rIdx != -1)
                    break;
                // 找sorted[l]的索引
                if (nums[i] == sorted[l] && lIdx == -1)
                    lIdx = i;
                // 找sorted[r]的索引,不能与sorted[l]的索引相同
                if (nums[i] == sorted[r] && rIdx == -1 && i != lIdx)
                    rIdx = i;
            }
            return new int[]{lIdx, rIdx};
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：循环
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++)
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[i] + nums[j] == target)
                        return new int[]{i, j};
            return new int[0];
        }
    }


    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        // put your test code here
//        solution.twoSum(new int[]{3, 2, 4}, 6);
//        solution.twoSum(new int[]{2, 5, 5, 11}, 10);
        solution.twoSum(new int[]{3, 2, 3}, 6);
    }
}