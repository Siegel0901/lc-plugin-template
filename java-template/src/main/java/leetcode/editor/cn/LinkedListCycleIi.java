package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class LinkedListCycleIi {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        /**
         * 思路一：集合去重
         *  1. 遍历链表，并将链表节点加入集合
         *  2. 若遍历时集合中存在该节点，则为环起始位置
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         * @param head 链表头节点
         * @return 环起始节点
         */
//        public ListNode detectCycle(ListNode head) {
//            Set<ListNode> set = new HashSet<>();
//            ListNode p = head;
//            while (p != null) {
//                if (set.contains(p)) {
//                    return p;
//                }
//                set.add(p);
//                p = p.next;
//            }
//            return null;
//        }

        /**
         * 思路二：快慢指针
         *  1. 使用快慢指针操作，快指针每次后移2步，慢指针每次后移1步
         *  2. 当快指针追上慢指针时，快指针指向头节点并与慢指针同步后移
         *  3. 当快慢指针相遇时，即为环开始节点
         *  证明：
         *      1. 变量：
         *          1.1. a表示头到环起点，b表示环的长度，c表示环起点->快慢指针首次相遇点的距离
         *          1.2. 头 <-a-> 环起点 <-c-> 相遇点 <-(b-c)-> 环起点
         *      2. 快慢指针相遇：
         *          2.1. 慢指针移动距离：s = a + c
         *          2.2. 快指针移动距离：f = a + nb + c(n为快指针绕环圈数)
         *          2.3. 关系:        f = 2s
         *                  a + nb + c = 2a + 2c
         *                          nb = a + c
         *                          a = nb - c
         *      3. 快指针回到头节点：
         *          3.1. slow距环起点距离：b - c
         *          3.2. fast距环起点距离：a = nb - c = (n - 1)b + (b - c)
         *      4. fast和slow同步移动:
         *          4.1 fast移动到环起点走过的距离：a = (n - 1)b + (b - c)
         *          4.2 此时slow走过的距离：a = (n - 1)b + (b - c)
         *              4.2.1 slow从相遇点开始走b - c的距离到环起点
         *              4.2.2 再从环起点出发，走(n - 1)b的距离到环起点
         *              4.2.3 最终slow与fast在环起点相遇
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         * @param head 链表头节点
         * @return 环起始节点
         */
        public ListNode detectCycle(ListNode head) {
            if (head == null)
                return null;

            ListNode fast = head;
            ListNode slow = head;

            // 快慢指针相遇
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) {
                    // 环存在，找到环起点
                    fast = head;
                    while (fast != slow) {
                        fast = fast.next;
                        slow = slow.next;
                    }
                    return fast;
                }
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi().new Solution();
        // put your test code here

    }
}