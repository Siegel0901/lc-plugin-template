package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class LinkedListCycle {

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
         * 思路：快慢指针
         *  1. 定义一个快指针，一个慢指针
         *  2. 快指针每次后移两步，慢指针每次后移一步
         *  3. 若链表中有环，则快指针最终会追上慢指针
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         * @param head 链表头节点
         * @return 是否有环
         */
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            // fast每次后移两步，所以需要判断fast.next是否为null
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (fast == slow) return true;
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LinkedListCycle().new Solution();
        // put your test code here

    }
}