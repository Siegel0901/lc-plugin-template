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
        /**
         * 思路：
         * 1. 用两个指针指向两个链表
         * 2. 对比两个指针指向结点的值大小
         * 3. 将对比结果插入到新链表尾部
         * 4. 当其中一个链表遍历完时，另一个链表中的所有结点插入到新链表尾部
         * 4. 返回新链表
         * 时间复杂度：O(m+n),m和n表示两个链表的长度
         * 空间复杂度：O(1)
         *
         * @param list1 链表1
         * @param list2 链表2
         * @return 新链表
         */
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(-1);
            ListNode p = dummy;
            ListNode p1 = list1, p2 = list2;
            while (p1 != null && p2 != null) {
                if (p1.val <= p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            p.next = p1 != null ? p1 : p2;
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
        // put your test code here
        int[] A1 = {-9, 3};
        int[] A2 = {5, 7};
        ListNode l1 = ListNode.createHead(A1);
        ListNode l2 = ListNode.createHead(A2);
        ListNode listNode = solution.mergeTwoLists(l1, l2);
        ListNode.print(listNode);
    }
}