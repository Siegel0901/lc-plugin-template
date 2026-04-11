package leetcode.editor.cn;

import java.util.Arrays;

public class VideoStitching {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：贪心
     * 1. 区间问题一定要按照start或end排序
     * 2. 因为题目要求[0,time]，故需要将所有区间按照start升序排序
     * 3. 若排序后的第一个start>0，则无法完成任务，返回-1
     * 4. 贪心策略：对于start相同的的区间，选择end大的那个，需要对end降序排序
     * 5. 确定好一个要选择的区间[x_start,x_end]后，要继续选择start小于等于x_end且end最大的区间（范围连续+贪心）
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int videoStitching(int[][] clips, int time) {
            // 按起点升序排序，起点相同按终点降序排序
            Arrays.sort(clips, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
            // 选择区间个数
            int count = 0;
            // curEnd记录当前区间的终点，nextEnd记录当前区间能够到达的最远终点
            int curEnd = 0, nextEnd = 0;
            int i = 0, n = clips.length;
            // 遍历起点小于等于当前区间终点的所有区间
            while (i < n && clips[i][0] <= curEnd) {
                // 找到终点最远的区间
                while (i < n && clips[i][0] <= curEnd) {
                    nextEnd = Math.max(nextEnd, clips[i][1]);
                    i++;
                }
                // 选择终点最远的区间
                count++;
                // 更新当前区间的终点
                curEnd = nextEnd;
                // 若已经可以达到time,返回区间个数
                if (curEnd >= time)
                    return count;
            }
            // 遍历完后,没有返回,说明最远终点无法达到time
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路：贪心
     * 1. 区间问题一定要按照start或end排序
     * 2. 因为题目要求[0,time]，故需要将所有区间按照start升序排序
     * 3. 若排序后的第一个start>0，则无法完成任务，返回-1
     * 4. 贪心策略：对于start相同的的区间，选择end大的那个，需要对end降序排序
     * 5. 确定好一个要选择的区间[x_start,x_end]后，要继续选择start小于等于x_end且end最大的区间（范围连续+贪心）
     * 时间复杂度：O(NlogN)
     * 空间复杂度：O(1)
     */
    class Solution1 {
        public int videoStitching(int[][] clips, int time) {
            // 按起点升序排序，起点相同按终点降序排序
            Arrays.sort(clips, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
            // 最小起点>0，无法到达[0,time]，返回-1
            if (clips[0][0] > 0)
                return -1;
            // x_end为当前最小起点区间的最大终点
            int x_end = clips[0][1];
            // 选择区间x
            int count = 1;
            // 更新区间后，需要判断终点是否已经超过了time
            if (x_end >= time)
                return count;
            // 当前能够达到的最远终点
            int next_end = x_end;
            for (int[] clip : clips) {
                // 获得当前区间的起点
                int start = clip[0];
                // 获得当前区间的终点
                int end = clip[1];
                // 保证连续的情况下,更新当前能够达到的最远终点
                if (start <= x_end)
                    next_end = Math.max(next_end, end);
                else {
                    // 若不连续了,则需要选择终点最远的那个区间
                    count++;
                    x_end = next_end;
                    // 更新区间后，需要判断终点是否已经超过了time
                    if (x_end >= time)
                        return count;
                    // 更新区间后，需要重新判断clips[i]的连续性
                    if (start <= x_end)
                        next_end = Math.max(next_end, end);
                    else
                        // 不连续，无法拼接
                        return -1;
                }
            }
            // 所有区间遍历完后，若当前能够到达的最远终点大于当前区间的终点,说明还需要再选择一个区间到达最远终点
            if (next_end > x_end) {
                x_end = next_end;
                count++;
            }
            // 若当前区间的终点大于等于time,则可以拼接
            return x_end >= time ? count : -1;
        }
    }


    public static void main(String[] args) {
        Solution solution = new VideoStitching().new Solution();
        // put your test code here
        solution.videoStitching(new int[][]{
//                {0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}
//                {0, 2}, {1, 6}, {3, 10}
//                {0, 2}, {4, 8}
                {5, 7}, {1, 8}, {0, 0}, {2, 3}, {4, 5}, {0, 6}, {5, 10}, {7, 10}
        }, 5);
    }
}