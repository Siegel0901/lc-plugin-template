package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MaximumProfitInJobScheduling {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：DP Table
     */
    class Solution {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int n = startTime.length;
            int[][] jobs = new int[n][3];
            for (int i = 0; i < n; i++)
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            // 按照endTime降序排序
            Arrays.sort(jobs, Comparator.comparingInt(job -> job[1]));
            // dp定义：dp(i,j)表示[0,i]时间区间内可以获得的最大利润为j
            TreeMap<Integer, Integer> dp = new TreeMap<>();
            // base case
            dp.put(0, 0);
            for (int[] job : jobs) {
                // 开始时间
                int start = job[0];
                // 结束时间
                int end = job[1];
                // 当前job利润
                int value = job[2];
                // 状态转移
                dp.put(end, Math.max(
                        // 选择这个job，利润为在当前job开始之前能获得的最大利润 + 当前job利润
                        dp.floorEntry(start).getValue() + value,
                        // 不选择这个job，保持现有的最大利润
                        dp.lastEntry().getValue()
                ));
            }
            return dp.lastEntry().getValue();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MaximumProfitInJobScheduling().new Solution();
        // put your test code here
        solution.jobScheduling(
                new int[]{1, 2, 3, 3},
                new int[]{3, 4, 5, 6},
                new int[]{50, 10, 40, 70}
        );
    }
}