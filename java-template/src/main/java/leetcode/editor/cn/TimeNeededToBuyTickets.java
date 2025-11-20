package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

public class TimeNeededToBuyTickets {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

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