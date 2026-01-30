package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class CapacityToShipPackagesWithinDDays {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：二分查找
         * 1. x为船的最低载重,x∈[max(weights[i]),sum(weights[i])]
         * 2. f(x)为船运载能力为x时，所需天数，f(x)非严格递减
         * 3. days为目标天数
         * 4. 求f(x)<=days的最小x，即寻找第一个满足f(x)<=days的x
         *
         * @param weights 包
         * @param days    天数
         * @return 最小船载重
         */
        public int shipWithinDays(int[] weights, int days) {
            int left = Arrays.stream(weights).max().getAsInt();
            int right = Arrays.stream(weights).sum();
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (f(weights, mid) <= days)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            return left;
        }

        public int f(int[] weights, int x) {
            int sum = 0;
            int days = 0;
            for (int i = 0; i < weights.length; i++) {
                // 判断带上当前货物是否超重
                if (sum + weights[i] > x) {
                    // 超重则送出，天数+1
                    days++;
                    // 清空船只内货物容量
                    sum = 0;
                    // 回退i，因为当前货物并没有被送出去
                    i--;
                } else {
                    // 未超重,入船
                    sum += weights[i];
                }
            }
            // 所有货物判断结束后,看看船上还有没有货物,有货则再送一次
            return sum > 0 ? days + 1 : days;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new CapacityToShipPackagesWithinDDays().new Solution();
        // put your test code here
        System.out.println(solution.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
    }
}