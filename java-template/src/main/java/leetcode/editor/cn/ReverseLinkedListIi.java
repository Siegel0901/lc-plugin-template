package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

public class ReverseLinkedListIi {

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
         * 思路一：取出[left,right]翻转后拼接
         * 1. 遍历链表，记录left节点和right.next节点
         * 2. 将left与前驱断开，right与后继断开
         * 3. 对left和right进行翻转后得到链表头节点last
         * 4. head的尾部拼接last，last尾部拼接right.next
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head  链表头节点
         * @param left  翻转区间的起始位置
         * @param right 翻转区间的结束位置
         * @return 翻转后的链表头节点
         */
//        public ListNode reverseBetween(ListNode head, int left, int right) {
//            ListNode dummy = new ListNode(-501, head);
//            // 找到left的前驱
//            ListNode leftPrev = getNode(dummy, left - 1);
//            // 将left的前驱与left断开，同时记录left节点
//            ListNode leftNode = leftPrev.next;
//            leftPrev.next = null;
//            // 找到right
//            ListNode rightNode = getNode(leftNode, right - left);
//            // 将right与后继断开，同时记录right的后继
//            ListNode rightNext = rightNode.next;
//            rightNode.next = null;
//            // 翻转left到right
//            ListNode last = reverseAll(leftNode);
//            // 前驱拼接翻转链表
//            leftPrev.next = last;
//            // 获得翻转链表的尾节点
//            ListNode tail = getTail(last);
//            // 翻转链表拼接后继
//            tail.next = rightNext;
//
//            return dummy.next;
//        }
//
//        /**
//         * 获取链表尾节点
//         *
//         * @param head 链表头结点
//         * @return 链表尾节点
//         */
//        public ListNode getTail(ListNode head) {
//            if (head == null || head.next == null)
//                return head;
//            ListNode p = head;
//            while (p.next != null)
//                p = p.next;
//            return p;
//        }
//
//
//        /**
//         * 获取从start开始往后走step步的结点
//         *
//         * @param start 起始节点
//         * @param step  步数
//         * @return 返回节点
//         */
//        public ListNode getNode(ListNode start, int step) {
//            ListNode p = start;
//            for (int i = 0; i < step && p != null; i++)
//                p = p.next;
//            return p;
//        }
//
//        public ListNode reverseAll(ListNode head) {
//            ListNode pre = null, cur = head;
//            while (cur != null) {
//                ListNode next = cur.next;
//                cur.next = pre;
//                pre = cur;
//                cur = next;
//            }
//            return pre;
//        }

        /**
         * 思路二：翻转前N个节点
         * 1. 找到leftPrev后翻转以leftNode为头节点的前right - left + 1个节点
         * 时间复杂度：O(n)
         * 空间复杂度：迭代O(1)，递归O(n)
         *
         * @param head  链表头结点
         * @param left  翻转区间的起始位置
         * @param right 翻转区间的结束位置
         * @return 新链表头结点
         */
//        public ListNode reverseBetween(ListNode head, int left, int right) {
//            ListNode dummy = new ListNode(-501, head);
//            // 找到left的前驱
//            ListNode leftPrev = getNode(dummy, left - 1);
//            // 将left的前驱与left断开，同时记录left节点
//            ListNode leftNode = leftPrev.next;
//            leftPrev.next = null;
//            // 翻转以leftNode为头节点的前right - left + 1个节点
//            leftPrev.next = reverseN(leftNode, right - left + 1);
//            return dummy.next;
//        }

//        /**
//         * 翻转前N个节点（迭代）
//         *
//         * @param head 链表头结点
//         * @param n    翻转的节点数
//         * @return 新链表头结点
//         */
//        public ListNode reverseN(ListNode head, int n) {
//            ListNode pre = null, cur = head;
//            // 翻转n次，cur == null用来避免n大于链表长度的情况
//            while (n > 0 && cur != null) {
//                // 记录cur的后继
//                ListNode next = cur.next;
//                // 修改cur的后继为原来的前驱
//                cur.next = pre;
//                // pre和cur后移
//                pre = cur;
//                cur = next;
//                // 更新n
//                n--;
//            }
//            // 翻转完尾节点为head，头节点为pre，第n + 1个节点是cur
//            if (head != null)
//                head.next = cur;
//            return pre;
//        }


        ListNode successor = null;

        /**
         * 翻转前N个节点（递归）
         *
         * @param head 链表头结点
         * @param n    翻转的节点数
         * @return 新链表头结点
         */
        public ListNode reverseN(ListNode head, int n) {
            // 当 n == 1 时，无需翻转，head == null || head.next == null 用来避免n大于链表长度的情况
            if (n == 1 || head == null || head.next == null) {
                // 记录后继（第 n + 1 个节点或null）
                successor = head == null ? null : head.next;
                return head;
            }
            // 递归翻转以head.next为首的链表的前 n - 1 个节点，返回翻转链表的头节点
            ListNode reversedHead = reverseN(head.next, n - 1);
            // head.next即为翻转链表的尾节点，将head插入到翻转链表尾部
            head.next.next = head;
            // head为翻转链表尾节点，插入后继
            head.next = successor;
            // 返回翻转链表的头节点
            return reversedHead;
        }

        /**
         * 思路三：递归翻转部分链表
         * 1. 通过递归找到leftPrev，边界：left == 1
         * 2. 翻转以leftNode为首的链表的前n个节点
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head  链表头结点
         * @param left  翻转区间的起始位置
         * @param right 翻转区间的结束位置
         * @return 新链表头结点
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            // 当left == 1时开始翻转以leftNode为首的链表的前N个节点
            if (left == 1 || head == null || head.next == null)
                return reverseN(head, right);
            // head.next翻转完成后作为head的后继
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi().new Solution();
        // put your test code here
        ListNode head = ListNode.createHead(new int[]{});
        ListNode reversed = solution.reverseN(head, 6);
        ListNode.print(reversed);
    }
}