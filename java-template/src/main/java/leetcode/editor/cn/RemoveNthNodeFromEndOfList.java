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
        /**
         * 思路：快慢指针
         * 1. 区间步数关系：
         * 1.1 头节点 <- (length-n)步 -> 倒数第n个节点 <- n步 -> null
         * 1.2 头节点 <- n步 -> 快指针走n步到达的节点 <- (length-n)步 -> null
         * 2. 快指针先走n步，走到null还需length - n，此时慢指针开始同步走
         * 3. 快指针走到null时，同步走了length - n步，慢指针指向倒数第n个节点
         * 4. 删除倒数第n个节点，就是要找倒数第n + 1个节点
         * 5. 使用dummy的原因：
         * findNthFromEnd(dummy, n+1) 返回带 dummy 链表的倒数第 n+1 个节点
         * findNthFromEnd(head, n+1) 返回真实链表的倒数第 n+1 个节点
         * 当 n == L 时：
         * 前者返回 dummy（因为 dummy 链表长度为 L+1，倒数第 L+1 个存在）
         * 后者返回 null（因为真实链表长度为 L，倒数第 L+1 个不存在）
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头节点
         * @param n    删除的节点位置
         * @return 链表头节点
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
/*
            // 不使用dummy
            ListNode preNth = findNthFromEnd(head, n + 1);
            // 如果倒数第 n + 1 个节点为null，则说明要删除头节点
            if (preNth == null)
                return head.next;
            preNth.next = preNth.next.next;
            return head;
*/
            // 使用dummy
            ListNode dummy = new ListNode(-1, head);
            ListNode preNth = findNthFromEnd(dummy, n + 1);
            preNth.next = preNth.next.next;
            return dummy.next;
        }

        public ListNode findNthFromEnd(ListNode head, int n) {
            ListNode fast = head;
            // 快指针先走n步
            for (int i = 0; i < n; i++) {
                if (fast == null) return null;
                fast = fast.next;
            }
            ListNode slow = head;
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }

        // 辅助函数：获取链表长度
        private int getListLength(ListNode head) {
            int length = 0;
            ListNode curr = head;
            while (curr != null) {
                length++;
                curr = curr.next;
            }
            return length;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        // put your test code here

    }
}