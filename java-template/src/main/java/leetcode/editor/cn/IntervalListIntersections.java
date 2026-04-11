package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersections {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：双指针
     * 1. 由于A和B已经排好序，故可以用双指针依次对比区间，得出交集
     * 2. 用i指向的(a1,a2)和j指向的(b1,b2)表示A和B中的两个区间
     * 3. 只有当 a2小于b1 或 b2小于a1 时不相交
     * 4. 取反得当 a2大于等于b1 且 b2大于等于a1 时相交
     * 5. 相交有4种情况
     * 5.1. i覆盖j：a1 <= b1 <= b2 <= a2，相交区间为(b1,b2)
     * 5.2. j覆盖i：b1 <= a1 <= a2 <= b2，相交区间为(a1,a2)
     * 5.3. j的左边与i重叠：a1 < b1 <= a2 < b2，相交区间为(b1,a2)
     * 5.4. j的右边与i重叠：b1 < a1 <= b2 < a2，相交区间为(a1,b2)
     * 5.5. 观察得相交区间为(max(a1,b1),min(a2,b2))
     * 6. i和j什么时候移动？看i和j谁的终点大，终点小的指针移动
     * 6.1. a2 > b2 -> j++
     * 6.2. a2 < b2 -> i++
     */
    class Solution {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            int i = 0, j = 0;
            List<int[]> res = new ArrayList<>();
            while (i < firstList.length && j < secondList.length) {
                int a1 = firstList[i][0], a2 = firstList[i][1];
                int b1 = secondList[j][0], b2 = secondList[j][1];
                if (a2 >= b1 && b2 >= a1)
                    res.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
                if (a2 > b2)
                    j++;
                else
                    i++;
            }
            return res.toArray(new int[res.size()][]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：合并后求重叠区间
     * 1. 将两个list合并为一个数组
     * 2. 按起点升序，起点相同，终点降序排序
     * 3. 此时会有三种情况
     * 3.1. 覆盖：小区间加入结果
     * 3.2. 重叠：取重叠部分加入结果,合并
     * 3.3. 不相交：更新为当前区间
     */
    class Solution1 {
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            int n = firstList.length;
            int m = secondList.length;
            int[][] merge = new int[n + m][];
            for (int i = 0; i < n + m; i++)
                merge[i] = i < n ? firstList[i] : secondList[i - n];
            Arrays.sort(merge, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
            int left = merge[0][0];
            int right = merge[0][1];
            List<int[]> list = new ArrayList<>();
            for (int i = 1; i < n + m; i++) {
                int[] intv = merge[i];
                // 情况一：覆盖
                if (left <= intv[0] && intv[1] <= right)
                    list.add(new int[]{intv[0], intv[1]});
                // 情况二:重叠
                if (intv[0] <= right && right < intv[1]) {
                    list.add(new int[]{intv[0], right});
                    right = intv[1];
                }
                // 情况三：不相交
                if (right < intv[0]) {
                    left = intv[0];
                    right = intv[1];
                }
            }
            int[][] res = new int[list.size()][];
            for (int i = 0; i < res.length; i++)
                res[i] = list.get(i);
            return res;
        }
    }


    public static void main(String[] args) {
        Solution solution = new IntervalListIntersections().new Solution();
        // put your test code here

    }
}