package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumSumCircularSubarray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 思路：前缀和 + 滑动窗口 + 单调队列 + 模拟环形数组
         * 1. 将nums数组翻倍模拟环形数组，并计算前缀和
         * 2. 子数组和：前缀和做差
         * 3. 在前缀和数组上做滑动窗口算法
         * 3.1. 什么时候扩大窗口？窗口大小 < nums.length
         * 3.2. 什么时候缩小窗口？窗口大小 == nums.length
         * 3.3. 什么时候更新结果？窗口有新元素加入时计算：新元素 - 窗口最小元素 = 最大和
         * 4. 如何得到窗口最小元素？单调队列
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param nums 数组
         * @return 最大和
         */
        public int maxSubarraySumCircular(int[] nums) {
            int[] preSum = getPreSum(nums);
            MonotonicQueue window = new MonotonicQueue();
            int maxSum = Integer.MIN_VALUE;
            window.offer(0);
            for (int i = 1; i < preSum.length; i++) {
                maxSum = Math.max(maxSum, preSum[i] - window.min());
                if (window.size() == nums.length)
                    window.poll();
                window.offer(preSum[i]);
            }
            return maxSum;
        }

        int[] getPreSum(int[] nums) {
            int n = nums.length;
            int[] preSum = new int[n * 2 + 1];
            for (int i = 1; i < preSum.length; i++)
                preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
            return preSum;
        }

        class MonotonicQueue {
            Deque<Integer> q;
            Deque<Integer> minQ;

            MonotonicQueue() {
                q = new ArrayDeque<>();
                minQ = new ArrayDeque<>();
            }

            void offer(int e) {
                q.offer(e);
                while (!minQ.isEmpty() && minQ.peekLast() > e)
                    minQ.pollLast();
                minQ.offer(e);
            }

            Integer poll() {
                Integer poll = q.poll();
                assert poll != null;
                if (poll.equals(minQ.peek()))
                    minQ.poll();
                return poll;
            }

            Integer min() {
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
        Solution solution = new MaximumSumCircularSubarray().new Solution();
        // put your test code here
        System.out.println(solution.maxSubarraySumCircular(new int[]{1}));
    }
}