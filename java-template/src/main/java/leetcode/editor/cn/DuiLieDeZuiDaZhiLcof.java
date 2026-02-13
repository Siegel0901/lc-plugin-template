package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Queue;

public class DuiLieDeZuiDaZhiLcof {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：单调队列
     * 1. 维护两个队列，一个原队列，一个单调队列
     * 2. 最大值为单调队列的对头
     * 3. 入队时，先加入原队列，再更新单调队列
     * 4. 出队时，先出队原队列得到出队元素，再判断单调队列中的队头是否与出队元素相等，若相等则单调队列也要出队
     */
    class Checkout {
        Queue<Integer> q = new ArrayDeque<>();
        ArrayDeque<Integer> maxQ = new ArrayDeque<>();

        public Checkout() {
        }

        public int get_max() {
            if (maxQ.isEmpty())
                return -1;
            return maxQ.peek();
        }

        public void add(int value) {
            q.offer(value);
            while (!maxQ.isEmpty() && maxQ.peekLast() < value)
                maxQ.pollLast();
            maxQ.offer(value);
        }

        public int remove() {
            if (q.isEmpty())
                return -1;
            Integer poll = q.poll();
            if (poll.equals(maxQ.peek()))
                maxQ.poll();
            return poll;
        }
    }

    /**
     * Your Checkout object will be instantiated and called as such:
     * Checkout obj = new Checkout();
     * int param_1 = obj.get_max();
     * obj.add(value);
     * int param_3 = obj.remove();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Checkout solution = new DuiLieDeZuiDaZhiLcof().new Checkout();
        // put your test code here

    }
}