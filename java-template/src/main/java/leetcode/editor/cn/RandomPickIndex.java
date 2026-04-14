package leetcode.editor.cn;

import java.util.Random;

public class RandomPickIndex {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：蓄水池抽样算法
     * P(第k个节点被选中)
     * = P(第k步选中) × P(第k+1步不替换) × P(第k+2步不替换) × ... × P(第n步不替换)
     * = (1/k) × [k/(k+1)] × [(k+1)/(k+2)] × ... × [(n-1)/n]
     * = (1/k) × (k/(k+1)) × ((k+1)/(k+2)) × ... × ((n-1)/n)
     * = 1/n  （分子分母相消）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    class Solution {
        int[] nums;
        Random rd;

        public Solution(int[] nums) {
            this.nums = nums;
            rd = new Random();
        }

        public int pick(int target) {
            int idx = -1, count = 0;
            for (int i = 0; i < nums.length; i++)
                if (nums[i] == target)
                    if (rd.nextInt(++count) == 0)
                        idx = i;
            return idx;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
//        Solution solution = new RandomPickIndex().new Solution();
        // put your test code here

    }
}