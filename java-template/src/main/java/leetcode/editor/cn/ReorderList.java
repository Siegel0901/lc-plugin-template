package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

import java.util.ArrayDeque;

public class ReorderList {

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

        public ListNode reverse(ListNode head) {
            ListNode cur = head, pre = null;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        /**
         * 思路一：翻转链表 + 合并链表
         * 1. 遍历链表，找到链表的中间节点，节点个数为偶数时取中间两节点的后一个节点
         * 2. 断开链表，原链表为链表1，以中间节点为头节点的链表为链表2
         * 3. 将链表2逆序
         * 4. 合并链表1和链表2
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         */
//        public void reorderList(ListNode head) {
//            ListNode dummy = new ListNode(0, head);
//            ListNode pre = dummy;
//            ListNode fast = dummy.next;
//            ListNode slow = dummy.next;
//            while (fast != null && fast.next != null) {
//                pre = pre.next;
//                slow = slow.next;
//                fast = fast.next.next;
//            }
//            pre.next = null;
//            ListNode reversed = reverse(slow);
//            dummy.next = null;
//            pre = dummy;
//            ListNode p1 = head;
//            ListNode p2 = reversed;
//            while (p1 != null && p2 != null) {
//                ListNode node1 = p1;
//                p1 = p1.next;
//                node1.next = null;
//                pre = insert(pre, node1);
//                ListNode node2 = p2;
//                p2 = p2.next;
//                node2.next = null;
//                pre = insert(pre, node2);
//            }
//            if (p1 != null) pre.next = p1;
//            if (p2 != null) pre.next = p2;
//        }

        /**
         * 思路二：栈
         * 1. 将所有节点加入栈得到逆序
         * 2. 遍历链表，在节点后插入栈顶元素
         * 3. 循环结束条件：
         * 3.1. 当前节点的后继 == 栈顶元素（偶数）
         * 3.2. 当前节点的后继 == 栈顶元素的后继（奇数）
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         */
        public void reorderList(ListNode head) {
            ArrayDeque<ListNode> stack = new ArrayDeque<>();
            ListNode p;
            for (p = head; p != null; p = p.next)
                stack.push(p);
            for (p = head; p != null; ) {
                ListNode next = p.next;
                ListNode lastNode = stack.pop();
                /*
                  当链表节点为偶数时，如（1,2），lastNode == next为true
                  当链表节点为奇数时，如（1，2，3）
                    p(1) -> next(2) -> lastNode(3) -> null
                    lastNode.next == null
                    接着执行插入操作
                    p(1) -> lastNode(3) -> next(2) -> lastNode(3)
                    继续执行，p指向next：p为2，next为3，lastNode为2
                    1 -> 3 -> p|lastNode(2) -> next(3)
                    lastNode.next = next
                 */
                if (lastNode == next || lastNode.next == next) {
                    lastNode.next = null;
                    break;
                }
                p.next = lastNode;
                lastNode.next = next;
                p = next;
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ReorderList().new Solution();
        // put your test code here
        solution.reorderList(ListNode.createHead(new int[]{1, 2, 3, 4, 5}));
    }
}