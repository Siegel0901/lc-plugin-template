package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

public class TimeNeededToBuyTickets {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 思路一：让下标排队
         * 1. 先将下标依次入队
         * 2. 遍历队列，每次取出下标票数-1，总秒数+1
         * 3. 若票数减到0，则对比下标是否为k
         * 4. 为k则返回总秒数
         * 5. 票数不为0继续入队
         * 6. 直至队列为空
         * 时间复杂度：O(n)，n为所有票数之和
         * 空间复杂度：O(m)，m为买票的人数
         *
         * @param tickets 票数
         * @param k       位置
         * @return 总秒数
         */
        public int timeRequiredToBuy(int[] tickets, int k) {
            Queue<Integer> q = new LinkedList<>();
            // 每个人的位置依次入队
            for (int i = 0; i < tickets.length; i++)
                q.offer(i);
            int second = 0;
            // 队列非空则代表有人买票
            while (!q.isEmpty()) {
                // 每次买票+1s
                second++;
                // 队首元素出队
                Integer i = q.poll();
                // 对应票数-1
                tickets[i]--;
                if (tickets[i] == 0 && i == k)
                    // 如果票买完且位置为k,则返回时间
                    return second;
                if (tickets[i] == 0)
                    // 票买完位置不为k，则下一个人买票
                    continue;
                // 还要买票则继续排队
                q.offer(i);
            }
            // 所有人都买完票所需的时间(k不存在)
            return second;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new TimeNeededToBuyTickets().new Solution();
        // put your test code here

    }
}