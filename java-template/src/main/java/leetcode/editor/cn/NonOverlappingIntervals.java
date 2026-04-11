package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：贪心
     * 1. 将所有区间按照end升序排序
     * 2. 找到end最小的区间x
     * 3. 删除与x相交的所有区间
     * 4. 重复2和3，删除的那些区间就是需要移除的区间，期间的x就是最大不相交区间子集
     * 贪心选择策略:end结束的越早,不相交区间子集越多,移除的区间越小
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            // 按照end升序排序
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
            // 第一个区间就是end最小的区间
            int x_end = intervals[0][1];
            // x的个数就是最大不相交子集的个数
            int count = 1;
            // 遍历所有区间
            for (int[] interval : intervals) {
                int start = interval[0];
                // 由于start<end,故x_end<start<end,代表后续已经没有与x相交的区间了
                if (start >= x_end) {
                    // 找到了新的x
                    x_end = interval[1];
                    count++;
                }
            }
            return intervals.length - count;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NonOverlappingIntervals().new Solution();
        // put your test code here

    }
}