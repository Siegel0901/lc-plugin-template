package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class RangeSumQueryImmutable {

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {
        int[] prefixSum;

        public NumArray(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 1; i < prefixSum.length; i++)
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        /**
         * 思路：前缀和
         * 用prefixSum[i]表示区间[0,i)，即nums[0,...,i-1]的累加和，prefixSum[0] = 0便于判断边界
         * 计算区间[i,j]和时，直接用preSum[j+1] - preSum[i]，无需特判i == 0
         * 时间复杂度：O(1)
         * 空间复杂度：O(n)
         *
         * @param left  起始索引
         * @param right 结束索引
         * @return sumRange
         */
        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        NumArray numArray = new RangeSumQueryImmutable().new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(numArray.sumRange(0, 2));
        // put your test code here

    }
}