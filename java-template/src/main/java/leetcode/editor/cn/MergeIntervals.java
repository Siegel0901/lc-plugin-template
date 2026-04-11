package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：利用性质:合并后的区间start一定是重叠区间里最小的,end一定是重叠区间里最大的
     * 1. 将区间按起点升序排序，起点相同的区间按终点降序排序
     * 2. 遍历所有区间:
     * 3.1. 若区间起点小于等于当前区间所能到达的最远终点，则更新合并区间的end
     * 3.2. 若区间起点大于当前区间所能到达的最远终点，则将该区间加入结果
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            // 临时记录结果
            List<int[]> list = new ArrayList<>();
            // 按起点升序排序,起点相同按终点降序排序
            Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            for (int[] interval : intervals) {
                // 列表为空，则加入第一个区间
                if (list.isEmpty()) {
                    list.add(new int[]{interval[0], interval[1]});
                    continue;
                }
                // 获得当前区间
                int lastIdx = list.size() - 1;
                int[] curr = list.get(lastIdx);
                /*
                * 若区间起点小于当前区间的终点，说明有重叠
                * 若区间终点大于当前区间的终点，则更新当前区间的终点
                * */
                if (interval[0] <= curr[1] && interval[1] > curr[1])
                    list.set(lastIdx, new int[]{curr[0], interval[1]});
                // 若区间起点大于当前区间的终点，说明当前区间的重叠区间已经处理完，需要处理下一个区间的重叠区间了
                if (interval[0] > curr[1])
                    list.add(new int[]{interval[0], interval[1]});
            }
            // 转为数组
            int[][] res = new int[list.size()][];
            for (int j = 0; j < res.length; j++)
                res[j] = list.get(j);
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：贪心
     * 1. 将区间按起点升序排序，起点相同的区间按终点降序排序
     * 2. 贪心选择：起点相同的区间，选择终点最远的区间
     * 3. 遍历所有区间:
     * 3.1. 若区间起点小于等于当前区间所能到达的最远终点，则更新当前区间所能到达的最远终点
     * 3.2. 若区间起点大于当前区间所能到达的最远终点，则将当前区间的起点和最远终点作为一个区间，加入结果
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution1 {
        public int[][] merge(int[][] intervals) {
            // 临时记录结果
            List<int[]> list = new ArrayList<>();
            // 按起点升序排序,起点相同按终点降序排序
            Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            // 记录当前区间的起点
            int curStart = intervals[0][0];
            // 当前区间的终点和能达到的最远终点
            int curEnd = intervals[0][1], nextEnd = intervals[0][1];
            int i = 1, n = intervals.length;
            // 遍历所有区间
            while (i < n) {
                if (intervals[i][0] <= curEnd) {
                    // 区间的起点小于等于当前区间终点,找到所能达到的最远终点
                    while (i < n && intervals[i][0] <= curEnd) {
                        nextEnd = Math.max(nextEnd, intervals[i][1]);
                        i++;
                    }
                    // 剩下的所有区间起点都大于当前区间终点,需要合并区间,更新当前区间终点为所能达到的最远终点
                    curEnd = nextEnd;
                } else {
                    // 区间的起点大于当前区间(合并后)终点,说明开始不连续,需要将不重叠区间加入结果
                    list.add(new int[]{curStart, nextEnd});
                    // 更新下一个区间的起点和终点
                    curStart = intervals[i][0];
                    curEnd = intervals[i][1];
                    nextEnd = intervals[i][1];
                    i++;
                }
            }
            // 最后一个合并区间加入结果
            list.add(new int[]{curStart, nextEnd});
            // 转为数组
            int[][] res = new int[list.size()][];
            for (int j = 0; j < res.length; j++)
                res[j] = list.get(j);
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new MergeIntervals().new Solution();
        // put your test code here
        solution.merge(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        });
    }
}