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
        public ListNode insert(ListNode pre, ListNode node) {
            ListNode next = pre.next;
            pre.next = node;
            node.next = next;
            return node;
        }

        /**
         * 思路：
         *  1. 新建两个链表d1，d2
         *  2. 遍历head结点，值小于x的结点放到d1，值大于等于x的结点放到d2
         *  3. 拼接d1和d2
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头节点
         * @param x 分割值
         * @return 分割后的链表头节点
         */
        public ListNode partition(ListNode head, int x) {
            ListNode dummy1 = new ListNode(0), p1 = dummy1;
            ListNode dummy2 = new ListNode(0), p2 = dummy2;
            ListNode p = head;
            while (p != null) {
                ListNode next = p.next;
                if (p.val < x) {
                    p1 = insert(p1, p);
                } else {
                    p2 = insert(p2, p);
                }
                p = next;
            }
            p1.next = dummy2.next;
            return dummy1.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PartitionList().new Solution();
        // put your test code here

    }
}