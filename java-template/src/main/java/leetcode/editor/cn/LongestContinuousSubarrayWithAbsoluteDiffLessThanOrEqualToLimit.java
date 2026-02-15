package leetcode.editor.cn;

import java.util.ArrayDeque;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：单调队列 + 滑动窗口
         * 1. 什么时候扩大窗口？窗口中的最大值与最小值之差 <= limit
         * 2. 什么时候缩小窗口？窗口中的最大值与最小值之差 > limit
         * 3. 什么时候更新结果？窗口中的最大值与最小值之差 <= limit
         * 4. 窗口用单调队列实现，维护最大值和最小值队列
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums  数组
         * @param limit 限制
         * @return 最长子数组的长度
         */
        public int longestSubarray(int[] nums, int limit) {
            MonotonicQueue<Integer> window = new MonotonicQueue<>();
            int maxLen = -1;
            for (int num : nums) {
                window.offer(num);
                while (!window.isEmpty() && Math.abs(window.max() - window.min()) > limit)
                    window.poll();
                maxLen = Math.max(maxLen, window.size());
            }
            return maxLen;
        }

        class MonotonicQueue<E extends Comparable<E>> {
            private final ArrayDeque<E> q;
            private final ArrayDeque<E> minQ;
            private final ArrayDeque<E> maxQ;

            public MonotonicQueue() {
                q = new ArrayDeque<>();
                minQ = new ArrayDeque<>();
                maxQ = new ArrayDeque<>();
            }

            public void offer(E e) {
                q.offer(e);
                while (!minQ.isEmpty() && minQ.peekLast().compareTo(e) > 0)
                    minQ.pollLast();
                minQ.offer(e);
                while (!maxQ.isEmpty() && maxQ.peekLast().compareTo(e) < 0)
                    maxQ.pollLast();
                maxQ.offer(e);
            }

            public E min() {
                return minQ.peek();
            }

            public E max() {
                return maxQ.peek();
            }

            public E poll() {
                E poll = q.poll();
                assert poll != null;
                if (poll.equals(minQ.peek()))
                    minQ.poll();
                if (poll.equals(maxQ.peek()))
                    maxQ.poll();
                return poll;
            }

            public int size() {
                return q.size();
            }

            public boolean isEmpty() {
                return q.isEmpty();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution();
        // put your test code here
        System.out.println(solution.longestSubarray(new int[]{8, 2, 4, 7}, 4));
    }
}