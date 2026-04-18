package leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SuperUglyNumber {

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * 思路:用优先级队列合并k个丑数质数倍数链表
     * 时间复杂度:O(n*logk)
     * 空间复杂度:O(n+k)
     * */
    class Solution {
        public int nthSuperUglyNumber(int n, int[] primes) {
            // 丑数序列
            int[] ugly = new int[n + 1];
            // 丑数序列索引
            int p = 1;
            // 优先级队列
            PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
            // 丑数的所有质数倍数链表
            long[][] list = new long[primes.length][];
            for (int i = 0; i < list.length; i++) {
                // 质数倍数链表{丑数质数倍数链表的当前节点(product),丑数质数倍数链表的指针(pp),质数倍数}
                list[i] = new long[]{1, 1, primes[i]};
                pq.offer(list[i]);
            }
            while (p <= n) {
                long[] min = pq.poll();
                // 去重
                if (ugly[p - 1] != min[0])
                    ugly[p++] = (int) min[0];
                // product = prime * ugly[(int) pp++]
                min[0] = min[2] * ugly[(int) min[1]++];
                // 加入优先级队列
                pq.offer(min);
            }
            return ugly[n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new SuperUglyNumber().new Solution();
        // put your test code here
        solution.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
    }
}