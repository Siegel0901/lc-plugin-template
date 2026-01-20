package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

import java.util.LinkedList;

public class AddTwoNumbersIi {

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
         * 思路一：链表翻转
         * 1. 遍历l1，l2，使用头插法得到翻转后的链表
         * 2. 遍历翻转链表，处理进位，相加得到结果
         * 3. 翻转结果链表并返回
         * 时间复杂度：O(m + n)，m和n为链表l1和l2的长度
         * 空间复杂度：O(max(m,n))
         *
         * @param l1 链表1
         * @param l2 链表2
         * @return 相加后的链表
         */
//        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//            return reverse(addTwoNumbersReverse(reverse(l1), reverse(l2)));
//        }
        public ListNode reverse(ListNode l) {
            ListNode dummy = new ListNode(-1);
            while (l != null) {
                ListNode next = dummy.next;
                ListNode lNext = l.next;
                dummy.next = l;
                l.next = next;
                l = lNext;
            }
            return dummy.next;
        }

        public ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1), tail = dummy;
            int carry = 0;
            while (l1 != null || l2 != null || carry > 0) {
                int sum = carry;
                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }
                carry = sum / 10;
                sum %= 10;
                tail.next = new ListNode(sum);
                tail = tail.next;
            }
            return dummy.next;
        }

        /**
         * 思路二：栈实现逆序
         * 1. 遍历两个链表，将两个链表节点放入两个栈中
         * 2. 弹栈得到低位，与进位相加后使用头插法插入新链表得到逆序结果
         * 3. 返回新链表
         * 时间复杂度：O(m + n)
         * 空间复杂度：O(m + n)
         *
         * @param l1 链表1
         * @param l2 链表2
         * @return 相加后的链表
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            LinkedList<Integer> s1 = new LinkedList<>();
            LinkedList<Integer> s2 = new LinkedList<>();
            for (ListNode p = l1; p != null; p = p.next)
                s1.push(p.val);
            for (ListNode p = l2; p != null; p = p.next)
                s2.push(p.val);
            ListNode dummy = new ListNode(-1);
            int carry = 0;
            while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
                int sum = carry;
                if (!s1.isEmpty())
                    sum += s1.pop();
                if (!s2.isEmpty())
                    sum += s2.pop();
                carry = sum / 10;
                sum %= 10;
                dummy.next = new ListNode(sum, dummy.next);
            }
            return dummy.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new AddTwoNumbersIi().new Solution();
        // put your test code here
//        ListNode l1 = ListNode.createHead(new int[]{7, 2, 4, 3});
//        ListNode l2 = ListNode.createHead(new int[]{5, 6, 4});
//        ListNode l3 = solution.addTwoNumbers(l1, l2);
//        ListNode.print(l3);
    }
}