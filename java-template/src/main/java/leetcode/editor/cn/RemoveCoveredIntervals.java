package leetcode.editor.cn;

import java.util.Arrays;

public class RemoveCoveredIntervals {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：排序
     * 1. 按起点升序排序，起点相同则按终点降序排序
     * 2. 会有三种情况
     * 2.1. 覆盖，删除
     * 2.2. 重叠，合并成一个大区间
     * 2.3. 不相交,更新当前区间
     */
    class Solution {
        public int removeCoveredIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            int left = intervals[0][0];
            int right = intervals[0][1];
            int count = 0;
            for (int i = 1; i < intervals.length; i++) {
                int[] intv = intervals[i];
                // 情况一：覆盖
                if (left <= intv[0] && right >= intv[1])
                    count++;
                // 情况二：重叠
                if (right >= intv[0] && right <= intv[1])
                    right = intv[1];
                // 情况三：不相交
                if (right < intv[0]) {
                    left = intv[0];
                    right = intv[1];
                }
            }
            return intervals.length - count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveCoveredIntervals().new Solution();
        // put your test code here

    }
}