package leetcode.editor.cn;

import java.util.Arrays;

public class CorporateFlightBookings {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：差分数组
         * 1. 航班座位数组是原数组
         * 2. 预定记录是差分数组，记录座位的增减情况
         * 时间复杂度：O(m + n)，m为预定记录数,n为航班数
         * 空间复杂度：O(n)
         *
         * @param bookings 预定记录
         * @param n        航班数
         * @return 航班座位数组
         */
        public int[] corpFlightBookings(int[][] bookings, int n) {
            Difference diff = new Difference(n);
            for (int[] booking : bookings)
                diff.increment(booking[0] - 1, booking[1] - 1, booking[2]);
            return diff.result();
        }

        class Difference {
            private final int[] diff;

            public Difference(int n) {
                diff = new int[n];
            }

            public void increment(int i, int j, int val) {
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
        Solution solution = new CorporateFlightBookings().new Solution();
        // put your test code here
        System.out.println(Arrays.toString(solution.corpFlightBookings(new int[][]{
                {1, 2, 10},
                {2, 3, 20},
                {2, 5, 25}
        }, 5)));

    }
}