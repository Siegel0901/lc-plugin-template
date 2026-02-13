package leetcode.editor.cn;

import java.util.ArrayDeque;

public class DailyTemperatures {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class IndexPair {
            int value;
            int index;

            public IndexPair(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        /**
         * 思路一：单调栈
         * 1. 逆序遍历数组，在当前元素的后面寻找下一个更高温度
         * 2. 找到第一个温度比当前温度高的元素，记录结果为两元素的索引之差，找不到则为0
         * 3. 当前元素及其索引入栈给前面的元素参考
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param temperatures 温度数组
         * @return 结果
         */
//        public int[] dailyTemperatures(int[] temperatures) {
//            int n = temperatures.length;
//            int[] res = new int[n];
//            ArrayDeque<IndexPair> stk = new ArrayDeque<>();
//            for (int i = n - 1; i >= 0; i--) {
//                while (!stk.isEmpty() && stk.peek().value <= temperatures[i])
//                    stk.pop();
//                res[i] = stk.isEmpty() ? 0 : stk.peek().index - i;
//                stk.push(new IndexPair(temperatures[i], i));
//            }
//            return res;
//        }

        /**
         * 思路二；同思路一，只不过不用自定义类，直接使用索引入栈
         *
         * @param temperatures 温度数组
         * @return 结果
         */
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] res = new int[n];
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i])
                    stk.pop();
                res[i] = stk.isEmpty() ? 0 : stk.peek() - i;
                stk.push(i);
            }
            return res;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new DailyTemperatures().new Solution();
        // put your test code here

    }
}