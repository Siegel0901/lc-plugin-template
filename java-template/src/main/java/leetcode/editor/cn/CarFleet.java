package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class CarFleet {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调栈
         * 1. 根据位置进行排序（位置和速度数组并非按位置前后有序）
         * 2. 计算从当前位置到终点所需的时间
         * 3. 利用单调栈对时间数组求上一个更大元素得到降序序列
         * 4. 上一个：正序遍历
         * 5. 更大元素：弹出小于等于当前元素的栈顶元素
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param target   目标位置
         * @param position 位置
         * @param speed    速度
         * @return 车队数量
         */
        public int carFleet(int target, int[] position, int[] speed) {
            int n = position.length;
            int[][] cars = new int[n][2];
            for (int i = 0; i < n; i++) {
                cars[i][0] = position[i];
                cars[i][1] = speed[i];
            }
            Arrays.sort(cars, Comparator.comparingInt(a -> a[0]));
            double[] time = new double[n];
            for (int i = 0; i < n; i++)
                time[i] = (target - cars[i][0]) / (cars[i][1] * 1.0);
            ArrayDeque<Double> stk = new ArrayDeque<>();
            for (double t : time) {
                while (!stk.isEmpty() && stk.peek() <= t)
                    stk.pop();
                stk.push(t);
            }
            return stk.size();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new CarFleet().new Solution();
        // put your test code here
        System.out.println(solution.carFleet(10, new int[]{0, 4, 2}, new int[]{2, 1, 3}));
    }
}