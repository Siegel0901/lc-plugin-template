package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloons {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路:贪心
     * 1. 本质上是在求最大不相交区间子集：射爆所有气球的最小弓箭数即求所有不相交的区间，相交的区间会被一支箭射爆
     * 2. 找出end最小的区间x
     * 3. 删除与x相交的区间
     * 4. 重复2和3,期间的x就是最大不相交子集
     * 贪心策略：end结束的越早,不相交区间子集越多
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int findMinArrowShots(int[][] points) {
            Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
            int x_end = points[0][1];
            int count = 1;
            for (int[] point : points) {
                int start = point[0];
                // 注意start<=x_end<=end的气球都会被x_end的箭射爆
                if (start > x_end) {
                    x_end = point[1];
                    count++;
                }
            }
            return count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfArrowsToBurstBalloons().new Solution();
        // put your test code here

    }
}