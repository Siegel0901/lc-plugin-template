package leetcode.editor.cn;

public class GasStation {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：滑动窗口
     * 1. 什么时候扩大窗口？窗口和>0
     * 2. 什么时候缩小窗口？窗口和<0
     * 3. 什么时候更新结果？窗口和>0时判断窗口长度是否为n
     * 4. 如何处理环形数组？数组延长一倍
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int right = 0, left = 0;
            int windowSum = 0;
            while (right < 2 * n) {
                int r = right++ % n;
                windowSum += gas[r] - cost[r];
                while (windowSum < 0) {
                    int l = left++ % n;
                    windowSum -= gas[l] - cost[l];
                }
                if (right - left == n)
                    return left;
            }
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：前缀和 + 贪心
     * 1. 求gas和cost差值的前缀和
     * 2. 最小前缀和的下一站就是始发站
     * 3. 接着判断所有差值的和是否>=0
     * 3.1. >0 -> 从始发站出发,能走完所有加油站且有剩余油量
     * 3.2. =0 -> 从始发站出发,刚好走完所有加油站
     * 3.3. <0 -> 油量消耗大于总加油量,无法环行
     * 示例:
     * gas  1   2   3   4   5
     * cost 3   4   5   1   2
     * rest -2  -2  -2  3   3
     * sum  -2  -4  -6  -3  0
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    class Solution1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int sum = 0, minSum = 0;
            int start = 0;
            for (int i = 0; i < n; i++) {
                sum += gas[i] - cost[i];
                if (sum < minSum) {
                    minSum = sum;
                    start = i + 1;
                }
            }
            if (sum < 0)
                return -1;
            return start % n;
        }
    }


    public static void main(String[] args) {
        Solution solution = new GasStation().new Solution();
        // put your test code here

    }
}