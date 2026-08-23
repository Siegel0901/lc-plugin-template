package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class TaskScheduler {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路二：模拟
     * 1. 统计每个任务出现的次数
     * 2. 使用大顶堆按剩余次数从高到低选择当前可执行的任务
     * 3. 使用冷却队列保存正在冷却的任务及其可再次执行的时间
     * 4. 每个时间片：
     * 4.1. 如果冷却队列头部的任务冷却完成，重新加入最大堆。
     * 4.2. 如果最大堆非空，取出一个任务执行，次数减1，若仍大于0则放入冷却队列（冷却n个时间单位）
     * 4.3. 否则CPU空闲
     * 5. 直到所有任务次数为0，返回总时间。
     * 时间复杂度：O(time),time 的下界是任务总数 N，上界受冷却时间 n 影响，最坏情况下可达到 O(N * n)。
     * 空间复杂度：O(1)
     * */
    class Solution {
        public int leastInterval(char[] tasks, int n) {
            int[] counts = new int[26];
            // 统计每个任务出现的次数
            for (char c : tasks)
                counts[c - 'A']++;
            // 建立大顶堆
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            // 将出现次数>0的任务加入大顶堆
            for (int cnt : counts)
                if (cnt > 0)
                    maxHeap.offer(cnt);
            // 冷却队列
            Deque<int[]> cooldown = new ArrayDeque<>();
            // 总时间
            int time = 0;
            // 循环直至所有任务完成
            while (!maxHeap.isEmpty() || !cooldown.isEmpty()) {
                time++;
                /*
                * 先看冷却队列中有没有冷却好的任务
                * 由于队列的FIFO以及一个时间片只执行一个任务，后来的任务的绝对时间肯定在后面
                * 所以每次只检查队首元素的绝对时间有没有到即可
                * */
                if (!cooldown.isEmpty() && cooldown.peek()[1] == time)
                    // 有则重新加入大顶堆
                    maxHeap.offer(cooldown.poll()[0]);
                // 再看大顶堆中的任务
                if (!maxHeap.isEmpty()) {
                    Integer cnt = maxHeap.poll();
                    // 如果运行完还有计数,则加入冷却队列
                    if (cnt - 1 > 0)
                        /*
                        * 冷却队列中存放的是：
                        * [0]：该任务剩余需要执行的次数
                        * [1]：该任务最早可以再次执行的时间（绝对时间）
                        * */
                        cooldown.offer(new int[]{cnt - 1, time + n + 1});
                }
            }
            // 返回总时间
            return time;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    /*
     * 思路一：贪心
     * 1. 找出现次数最多的任务，以它为“骨架”排布
     * 1.1. 以tasks = [A,A,A,B,B,B],n = 2为例，A出现了3次
     * 1.2. A _ _ A _ _ A
     * 2. 这个骨架决定了最短时间的下界：
     * 2.1. 有maxFreq - 1个间隔段，每段长度为n
     * 2.2. 最后还有1个位置放最后一个A
     * 2.3. 骨架长度 = (maxFreq - 1) * (n + 1) + 1
     * 3. 如果有多个任务并列最多(A和B都是3次)，把B填进去
     * 3.1. A B _ A B _ A B
     * 3.2. 末尾多出来的B要算上，则骨架长度为：(maxFreq - 1) * (n + 1) + count
     * 3.3. count就是并列最多的任务数
     * 4. 最后一种情况：任务种类很多，冷却时间很短，所有间隔都能被填满，不需要空闲，此时最短时间间隔为：tasks.length
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * */
    class Solution1 {
        public int leastInterval(char[] tasks, int n) {
            int[] taskSum = new int[26];
            for (char task : tasks)
                taskSum[task - 'A']++;
            int max = 0, cnt = 0;
            for (int num : taskSum) {
                if (num > max) {
                    max = num;
                    cnt = 1;
                } else if (num == max && num > 0)
                    cnt++;
            }
            return Math.max((max - 1) * (n + 1) + cnt, tasks.length);
        }
    }


    public static void main(String[] args) {
        Solution solution = new TaskScheduler().new Solution();
        // put your test code here

    }
}