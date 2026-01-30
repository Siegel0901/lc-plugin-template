package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class KokoEatingBananas {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：二分查找
         * 1. x：速度(根/小时), x ∈ [1, max(piles[i])], i ∈ [0, n - 1]
         * 1.1. x的最大值也可以是piles[i]的上界10^9
         * 1.2. 推荐使用上界，因为找最大值需要消耗时间
         * 2. f(x)：f(x) = n堆香蕉(根) / 速度(根/小时) = 时间
         * 3. target：目标时间h
         * 4. 求f(x)<=h的最小x，即左边界
         * 时间复杂度：O(logn)
         * 空间复杂度：O(1)
         *
         * @param piles 香蕉
         * @param h     小时
         * @return 最小速度
         */
        public int minEatingSpeed(int[] piles, int h) {
            int left = 1;
            int right = 1000000000 + 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (f(piles, mid) <= h)
                    right = mid;
                else
                    left = mid + 1;
            }
            return left;
        }

        /**
         * 返回用x的速度，吃完n堆香蕉所需的时间。
         * 返回值为long的原因：
         * piles最大长度为10^4，最大元素值为10^9
         * int的最大值大概为2*10^9
         * 使用long类型避免int类型溢出
         *
         * @param piles 香蕉
         * @param x     速度
         * @return 用x的速度，吃完n堆香蕉所需时间
         */
        public long f(int[] piles, int x) {
            long hours = 0;
            for (int pile : piles) {
                hours += pile / x;
                if (pile % x != 0)
                    hours++;
            }
            return hours;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new KokoEatingBananas().new Solution();
        // put your test code here
        System.out.println(solution.minEatingSpeed(new int[]{805306368, 805306368, 805306368}, 1000000000));
    }
}