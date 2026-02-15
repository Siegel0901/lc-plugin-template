package leetcode.editor.cn;

import java.util.ArrayDeque;

public class ShortestSubarrayWithSumAtLeastK {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：前缀和 + 滑动窗口 + 单调队列
         * 1. 求nums数组的前缀和preSum
         * 2. 子数组[left,right]的和为preSum[right + 1] - preSum[left]
         * 3. 对preSum使用滑动窗口算法
         * 3.1. 什么时候扩大窗口？preSum[right] - 窗口最小元素 < k
         * 3.2 什么时候缩小窗口？preSum[right] - 窗口最小元素 >= k
         * 3.3. 什么时候更新结果？preSum[right] - 窗口最小元素 >= k
         * 4.如何得到窗口最小元素？单调队列
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         * 【求和范围可能超过int类型返回，需要使用long类型】
         *
         * @param nums 数组
         * @param k    目标和
         * @return 最短的子数组长度
         */
        public int shortestSubarray(int[] nums, int k) {
            long[] preSum = getPreSum(nums);
            MonotonicQueue window = new MonotonicQueue();
            int minLen = Integer.MAX_VALUE;
            for (long sum : preSum) {
                window.offer(sum);
                while (!window.isEmpty() && sum - window.min() >= k) {
                    Long min = window.min();
                    long poll = window.poll();
                    // 最小元素出队时，得到符合要求的子数组长度，更新结果
                    if (min == poll)
                        minLen = Math.min(minLen, window.size());
                }
            }
            return minLen == Integer.MAX_VALUE ? -1 : minLen;
        }

        public long[] getPreSum(int[] nums) {
            long[] preSum = new long[nums.length + 1];
            for (int i = 1; i < preSum.length; i++)
                preSum[i] = preSum[i - 1] + nums[i - 1];
            return preSum;
        }

        class MonotonicQueue {
            ArrayDeque<Long> q;
            ArrayDeque<Long> minQ;

            MonotonicQueue() {
                q = new ArrayDeque<>();
                minQ = new ArrayDeque<>();
            }

            void offer(long e) {
                q.offer(e);
                while (!minQ.isEmpty() && minQ.peekLast() > e)
                    minQ.pollLast();
                minQ.offer(e);
            }

            long poll() {
                Long poll = q.poll();
                assert poll != null;
                if (poll.equals(minQ.peek()))
                    minQ.poll();
                return poll;
            }

            Long min() {
                return minQ.peek();
            }

            int size() {
                return q.size();
            }

            boolean isEmpty() {
                return q.isEmpty();
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ShortestSubarrayWithSumAtLeastK().new Solution();
        // put your test code here
        System.out.println(solution.shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167));
    }
}