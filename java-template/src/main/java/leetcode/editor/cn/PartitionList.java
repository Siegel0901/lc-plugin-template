package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class PartitionList {

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
        public ListNode insert(ListNode pre, ListNode q) {
            ListNode next = pre.next;
            pre.next = q;
            q.next = next;
            return q;
        }

        public ListNode partition(ListNode head, int x) {
            /**
             * 思路：
             *  遍历链表，将val小于x的结点放到head1链表，val大于等于x的结点放到head2链表
             *  最后拼接head1链表和head2链表的结点为新链表
             */
            ListNode head1 = new ListNode(0);
            ListNode head2 = new ListNode(0);
            ListNode tail1 = head1;
            ListNode tail2 = head2;
            while (head != null) {
                ListNode temp = head;
                head = head.next;
                if (temp.val < x) {
                    tail1 = insert(tail1, temp);
                } else {
                    tail2 = insert(tail2, temp);
                }
            }
            tail1.next = head2.next;
            return head1.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PartitionList().new Solution();
        // put your test code here

    }
}