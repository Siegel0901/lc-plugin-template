package leetcode.editor.cn;

import java.util.Random;

public class RandomPickWithWeight {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路：前缀和 + 二分查找
     * 1. 将所有w[i]视为线段长度，从中随机选择一个线段，选择的线段属于w[i]的概率 为 w[i] / sum(w)
     * 2. 如何构造权重线段数组？前缀和数组
     * 2.1. 如w=[1,3,2,1]，则preSum=[0,1,4,6,7],在[1,7]中随机生成一个数即可选取一个线段
     * 3. 如何知道该线段属于哪个w[i]？二分查找
     * 3.1. 在preSum中二分查找第一个大于随机数的元素索引idx
     * 3.2. idx-1即为随机选择的下标
     * */
    class Solution {
        int[] preSum;
        Random rd;

        public Solution(int[] w) {
            calculatePreSum(w);
            rd = new Random();
        }

        void calculatePreSum(int[] w) {
            int n = w.length;
            preSum = new int[n + 1];
            for (int i = 1; i <= n; i++)
                preSum[i] = preSum[i - 1] + w[i - 1];
        }

        public int pickIndex() {
            int n = preSum.length - 1;
            // [0,preSum[n])+1=[1,preSum[n]+1)=[1,perSum[n]]
            int target = rd.nextInt(preSum[n]) + 1;
            // perSum[0]为占位符，需要舍去
            return binary_search(preSum, target) - 1;
        }

        private int binary_search(int[] nums, int target) {
            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                // 找第一个大于随机数的索引
                if (nums[mid] >= target)
                    right = mid;
                else
                    left = mid + 1;
            }
            return left;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new RandomPickWithWeight().new Solution();
        // put your test code here

    }
}