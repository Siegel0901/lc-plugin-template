package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfRecentCalls {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：维护一个队列
     * 1. 将每次请求的t入队
     * 2. 遍历队列，判断在[t-3000,t]之间的元素的个数count
     * 3. 队首出队再入队，共size次
     * 4. 返回count
     * 单个ping时间复杂度：O(n)，n为ping次数
     * 所有ping时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     * 【Time Limit Exceeded】
     */
//    class RecentCounter {
//        Queue<Integer> queue;
//
//        public RecentCounter() {
//            queue = new LinkedList<>();
//        }
//
//        public int ping(int t) {
//            queue.offer(t);
//            int size = queue.size();
//            int count = 0;
//            int countT = 0;
//            while (size-- > 0 && countT < 3001) {
//                Integer poll = queue.poll();
//                if (poll >= t - 3000 && poll <= t) {
//                    count++;
//                    countT++;
//                }
//                queue.offer(poll);
//            }
//            return count;
//        }
//    }

    /**
     * 思路二：用队列维护一个滑动窗口，只保留过去3000毫秒内的所有请求
     * 1. 将每次的请求时间t入队
     * 2. 删除队列中所有小于t-3000的元素（过去3000毫秒外）
     * 3. 队列中的元素个数即是过去3000毫秒内的请求个数
     * 单个ping时间复杂度：O(1)
     * 所有ping时间复杂度：O(n)，n为ping次数
     * 空间复杂度：O(1)
     */
    class RecentCounter {
        Queue<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000)
                queue.poll();
            return queue.size();
        }
    }

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
//leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        RecentCounter solution = new NumberOfRecentCalls().new RecentCounter();
        // put your test code here

    }
}