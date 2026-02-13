package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindowMaximum {

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        class MonotonicQueue {
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            public void push(int e) {
                /*
                 * 弹出比当前元素小的元素
                 * 不能取等于，若存在重复元素为最大值的情况，需依次弹出
                 * */
                while (!queue.isEmpty() && queue.peekLast() < e)
                    queue.pollLast();
                queue.offerLast(e);
            }

            public Integer max() {
                // 队首元素即为最大元素
                return queue.peek();
            }

            public void pop(int e) {
                // 若队首元素为要移出的元素，则弹出队首
                if (!queue.isEmpty() && e == queue.peek())
                    queue.poll();
            }
        }

        /**
         * 思路：单调队列
         * 1. 实现单调队列类，具有push，pop，max等方法
         * 2. 窗口即为单调队列，窗口每次移动调用max方法得到最大值
         * 时间复杂度：O(n)
         * 空间复杂度：O(k)
         *
         * @param nums 数组
         * @param k    窗口大小
         * @return 结果数组
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            MonotonicQueue window = new MonotonicQueue();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // 窗口未满则加入
                if (i < k - 1) {
                    window.push(nums[i]);
                } else {
                    window.push(nums[i]);
                    // 窗口满了，收集最大元素
                    res.add(window.max());
                    // 移出旧元素
                    window.pop(nums[i - k + 1]);
                }
            }
            return res.stream().mapToInt(Integer::intValue).toArray();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum().new Solution();
        // put your test code here
        int[] ints = solution.maxSlidingWindow(new int[]{-7, -8, 7, 5, 7, 1, 6, 0}, 4);
        System.out.println(Arrays.toString(ints));
    }
}