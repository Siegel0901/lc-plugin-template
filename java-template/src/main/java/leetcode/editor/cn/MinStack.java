package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路二：新增最小栈
     * 1. 维护最小栈，最小栈中的每个元素表示原始栈中元素作为栈顶时的最小元素
     */
    class MinStack2 {
        Deque<Integer> stk;
        Deque<Integer> minStk;

        public MinStack2() {
            stk = new ArrayDeque<>();
            minStk = new ArrayDeque<>();
        }

        /*
         * 原始方案
         * */
//        public void push(int val) {
//            stk.push(val);
//            if (minStk.isEmpty() || val <= minStk.peek())
//                // val为全栈最小
//                minStk.push(val);
//            else
//                // 最小值不变
//                minStk.push(minStk.peek());
//        }
//
//        public void pop() {
//            stk.pop();
//            minStk.pop();
//        }
        /*
         * 优化方案
         * */
        public void push(int val) {
            stk.push(val);
            // minStk只记录最小值
            if (minStk.isEmpty() || val <= minStk.peek())
                // val为全栈最小
                minStk.push(val);
        }

        public void pop() {
            // 删除最小值时minStk才弹出元素
            if (stk.peek().equals(minStk.peek()))
                minStk.pop();
            stk.pop();
        }

        public int top() {
            return stk.peek();
        }

        public int getMin() {
            return minStk.peek();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路一：用队列更新min
     * 1. 维护min变量为队列中元素的最小值
     * 2. 若pop时删除的是min元素，则遍历队列更新min元素
     */
    class MinStack1 {
        Deque<Integer> stk;
        Integer min;

        public MinStack1() {
            stk = new ArrayDeque<>();
            min = null;
        }

        public void push(int val) {
            stk.push(val);
            if (min == null)
                min = val;
            else
                min = Math.min(min, val);
        }

        public void pop() {
            Integer pop = stk.pop();
            if (min.equals(pop))
                updateMin();
        }

        private void updateMin() {
            min = stk.peek();
            int count = 0;
            while (count != stk.size()) {
                Integer poll = stk.poll();
                min = Math.min(min, poll);
                stk.offer(poll);
                count++;
            }
        }

        public int top() {
            return stk.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {
        // put your test code here

    }
}