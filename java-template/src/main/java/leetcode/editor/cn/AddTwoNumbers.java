package leetcode.editor.cn;

import leetcode.editor.common.*;

public class AddTwoNumbers {

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
         * 思路一：
         * 1. 双指针l1，l2遍历链表，进位carry初始为0，初始化结果链表dummy
         * 2. l1，l2的值与进位相加作为节点加入dummy，大于10则进位carry为1，否则carry为0
         * 3. l1，l2后移
         * 4. l1和l2都为null且进位为0结束循环
         * 时间复杂度：O(max(m,n)),m，n为链表长度
         * 空间复杂度：O(max(m,n))
         *
         * @param l1 链表1
         * @param l2 链表2
         * @return 新链表头结点
         */
//        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//            int carry = 0;
//            ListNode dummy = new ListNode(-1), p = dummy;
//            // l1和l2都为null且进位为0结束循环
//            while (l1 != null || l2 != null || carry > 0) {
//                int res = carry;
//                if (l1 != null) {
//                    res += l1.val;
//                    l1 = l1.next;
//                }
//                if (l2 != null) {
//                    res += l2.val;
//                    l2 = l2.next;
//                }
//                carry = res / 10;
//                res %= 10;
//                p.next = new ListNode(res);
//                p = p.next;
//            }
//            return dummy.next;
//        }

        /**
         * 思路二：同思路一，只不过使用for循环
         *
         * @param l1 链表1
         * @param l2 链表2
         * @return 新链表头结点
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p1 = l1, p2 = l2;
            // 先遍历完一个链表
            for (; p1 != null && p2 != null; p1 = p1.next, p2 = p2.next)
                calculate(p1.val + p2.val);
            // 处理剩余链表节点
            for (; p1 != null; p1 = p1.next)
                calculate(p1.val);
            for (; p2 != null; p2 = p2.next)
                calculate(p2.val);
            // 处理最后的进位
            if (carry != 0)
                tail = insert(tail, carry);
            return dummy.next;
        }

        public int carry = 0;
        public ListNode dummy = new ListNode(-1), tail = dummy;

        // 链表节点插入
        public ListNode insert(ListNode prev, int val) {
            ListNode node = new ListNode(val);
            ListNode next = prev.next;
            prev.next = node;
            node.next = next;
            return node;
        }

        // 计算节点值
        public void calculate(int val) {
            int sum = carry + val;
            carry = sum / 10;
            tail = insert(tail, sum % 10);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        // put your test code here

    }
}