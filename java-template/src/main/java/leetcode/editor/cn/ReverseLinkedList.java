package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

public class ReverseLinkedList {

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
         * 思路一：头插法翻转链表
         * 1. 初始化虚拟头结点dummy
         * 2. 遍历链表，将节点插入至dummy后
         * 3. 遍历完毕返回dummy.next得到翻转链表
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
//        public ListNode reverseList(ListNode head) {
//            ListNode dummy = new ListNode(-1);
//            while (head != null) {
//                // 保存新链表的后继
//                ListNode dNext = dummy.next;
//                // 保存当前链表的后继
//                ListNode hNext = head.next;
//                // 头插法插入节点
//                dummy.next = head;
//                // 拼接后继
//                dummy.next.next = dNext;
//                // 当前节点后移
//                head = hNext;
//            }
//            return dummy.next;
//        }

        /**
         * 思路二：后序遍历递归实现
         * 1. 后序遍历单链表得到翻转后的链表
         * 2. 相当于元素入栈再出栈得到逆序节点
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
//        public ListNode reverseList(ListNode head) {
//            traverse(head);
//            p.next = null;
//            return dummy.next;
//        }
//
//        // 虚拟头结点以及工作指针
//        public ListNode dummy = new ListNode(-1), p = dummy;
//
//        public void traverse(ListNode head) {
//            // 递归边界
//            if (head == null)
//                return;
//            traverse(head.next);
//            // 逆序插入到新链表中
//            p.next = head;
//            // 工作指针后移
//            p = p.next;
//        }

        /**
         * 思路三:原地翻转
         * 1. 通过pre（前驱），cur（当前节点）双指针实现原地翻转
         * 2. pre初始为null，cur初始为head，遍历单链表直至cur为null
         * 3. 每次遍历操作：
         * 3.1. 记录cur的后继
         * 3.2. 将cur的后继设为pre（实现翻转）
         * 3.3. cur和pre后移
         * 4. 当cur为null时，pre指向cur的前驱，也就是原先链表的尾节点
         * 5. 返回pre作为翻转链表的头节点
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
//        public ListNode reverseList(ListNode head) {
//            ListNode pre = null, cur = head;
//            while (cur != null) {
//                // 记录当前节点的后继
//                ListNode next = cur.next;
//                // 将当前节点的后继指向原来的前驱
//                cur.next = pre;
//                // 前驱后移
//                pre = cur;
//                // 当前节点后移
//                cur = next;
//            }
//            // cur为null时,pre为原先链表的尾节点,返回pre作为翻转链表的头节点
//            return pre;
//        }

        /**
         * 思路四:分解子问题递归实现
         * 1. 把翻转head分解为翻转head.next，最后插入head得到
         * 2. 递归边界：head为null或head.next为null
         * 3. 函数返回翻转链表的头节点，翻转链表的尾节点即为head.next
         * 例子：
         * 1 -> 2 -> 3 -> null
         * 1 -> reverseList(2 -> 3 -> null)
         * 1[head] -> (2[head.next] <- 3[reversedHead]) {2[head.next] -> null[head.next.next]}
         * 1[head] <- 2[head.next] <- 3[reversedHead] {head.next.next = head}
         * null <- 1[head] <- 2 <- 3[reversedHead] {head.next = null}
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
        public ListNode reverseList(ListNode head) {
            // 链表为空或只有一个节点
            if (head == null || head.next == null)
                return head;
            // 将以head.next为首的链表翻转，得到翻转链表的头节点
            ListNode reversedHead = reverseList(head.next);
            // head.next即为翻转链表的尾节点，插入head
            head.next.next = head;
            // 此时head作为尾节点，需要将head.next置为null
            head.next = null;
            // 返回翻转链表的头节点
            return reversedHead;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        // put your test code here

    }
}