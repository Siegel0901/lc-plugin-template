package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class RemoveNthNodeFromEndOfList {

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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (n == 0 || head == null)
                return head;
            ListNode realHead = new ListNode(0, head);
            ListNode pre = realHead;
            ListNode slow = realHead.next;
            ListNode fast = realHead.next;
            while (n > 0 && fast != null) {
                fast = fast.next;
                n--;
            }
            if (n > 0) return head;
            while (fast != null) {
                pre = pre.next;
                slow = slow.next;
                fast = fast.next;
            }
            if (slow != null) {
                pre.next = slow.next;
            }
            return realHead.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        // put your test code here

    }
}