package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class IntersectionOfTwoLinkedLists {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        /**
         * 思路一：集合去重
         *  1. 遍历两个链表的所有节点，加入集合
         *  2. 第一个集合中已存在的节点，即为相交的起始节点
         * 时间复杂度：O(m + n)
         * 空间复杂度：O(m + n)
         * @param headA 链表1
         * @param headB 链表2
         * @return 相交的结点
         */
//        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//            Set<ListNode> set = new HashSet<>();
//            ListNode p = headA;
//            while (p != null) {
//                set.add(p);
//                p = p.next;
//            }
//            p = headB;
//            while (p != null) {
//                if (set.contains(p))
//                    return p;
//                set.add(p);
//                p = p.next;
//            }
//            return null;
//        }

        /**
         * 思路二：链表长度对齐
         * 1. 用两个指针同时遍历各自链表
         * 2. 当指针指向null时，接着遍历另一个链表的头结点
         * 3. 由于两个链表相交，则长度对齐后，相交部分在相同的位置
         * 4. 故两个指针首次指向的相同节点，即为相交的起始节点
         * 例子：
         * l1 -> 1  2  3  4  *5 *3 *4
         * l2 -> 5  3  4  *1 *2 *3 *4
         * 时间复杂度：O(m + n)
         * 空间复杂度：O(1)
         *
         * @param headA 链表1
         * @param headB 链表2
         * @return 相交的结点
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode l1 = headA, l2 = headB;
            while (l1 != l2) {
                l1 = l1 == null ? headB : l1.next;
                l2 = l2 == null ? headA : l2.next;
            }
            return l1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoLinkedLists().new Solution();
        // put your test code here

    }
}