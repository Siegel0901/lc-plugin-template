package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class MiddleOfTheLinkedList {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /**
         * 思路：快慢指针
         *  1. 若链表长度为偶数，则fast为null时，slow走到中间节点两个节点的第二个节点
         *  2. 若链表长度为奇数，则fast走到链表尾，fast.next为null时，slow走到中间节点
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         * @param head 链表头节点
         * @return 中间节点
         */
        public ListNode middleNode(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MiddleOfTheLinkedList().new Solution();
        // put your test code here

    }
}