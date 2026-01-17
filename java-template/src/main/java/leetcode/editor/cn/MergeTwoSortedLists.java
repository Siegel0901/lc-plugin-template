package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class MergeTwoSortedLists {

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
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // 头结点
            ListNode tail = new ListNode(0);
            ListNode head = tail;
            while (list1 != null && list2 != null) {
                // 判断大小,值小的结点移动到head后面
                if (list1.val <= list2.val) {
                    tail.next = list1;
                    list1 = list1.next;
                } else {
                    tail.next = list2;
                    list2 = list2.next;
                }
                tail = tail.next;
            }
            if (list1 != null) tail.next = list1;
            if (list2 != null) tail.next = list2;
            return head.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
        // put your test code here

    }
}