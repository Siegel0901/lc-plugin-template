package leetcode.editor.cn;

import java.util.Arrays;

public class CarPooling {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：差分数组
         * 1. 定义nums[i]表示到i站前车上的乘客数
         * 2. trip[i] = [numPassengers, from,to]表示在nums[from,to)区间增加numPassengers的乘客数
         * 时间复杂度：O(n + m)，n为站点数，m为乘客旅途记录数
         * 空间复杂度：O(n)
         *
         * @param trips    旅途记录数
         * @param capacity 车座位数
         * @return 是否能完成旅途
         */
        public boolean carPooling(int[][] trips, int capacity) {
            Difference diff = new Difference(new int[1001]);
            for (int[] trip : trips)
                diff.increase(trip[1], trip[2] - 1, trip[0]);
            int result = Arrays.stream(diff.result()).max().getAsInt();
            return result <= capacity;
        }

        class Difference {
            private final int[] diff;

            public Difference(int[] nums) {
                this.diff = new int[nums.length];
                diff[0] = nums[0];
                for (int i = 1; i < nums.length; i++)
                    diff[i] = nums[i] - nums[i - 1];
            }

            public void increase(int i, int j, int val) {
                diff[i] += val;
                if (j + 1 < diff.length)
                    diff[j + 1] -= val;
            }

            public int[] result() {
                int[] res = new int[diff.length];
                res[0] = diff[0];
                for (int i = 1; i < diff.length; i++)
                    res[i] = res[i - 1] + diff[i];
                return res;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new CarPooling().new Solution();
        // put your test code here

    }
}