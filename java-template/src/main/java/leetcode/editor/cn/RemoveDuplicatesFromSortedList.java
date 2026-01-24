package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class RemoveDuplicatesFromSortedList {

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
         * 思路一:双指针遍历赋值（同有序数组去重）
         * 1. i和j初始化为head
         * 2. 判断i和j的节点值是否相等
         * 3. 若不相等,则i节点后移,并将j节点值赋值给i所在节点
         * 4. j后移至null时,将i的后继置为null,返回head
         * 时间复杂度：O(n)
         * 空间复杂度：O(1    )
         *
         * @param head 链表头结点
         * @return 去重后的链表头结点
         */
//        public ListNode deleteDuplicates(ListNode head) {
//            ListNode i = head, j = head;
//            while (j != null) {
//                if (i.val != j.val) {
//                    i = i.next;
//                    i.val = j.val;
//                }
//                j = j.next;
//            }
//            // 当head为null时，i为null
//            if (i != null)
//                i.next = null;
//            return head;
//        }

        /**
         * 思路二：双指针删除链表节点
         * 1. 初始化无重复区尾节点tail和工作节点p为head
         * 2. 当head为null时，返回null
         * 3. 用p指针遍历链表
         * 4. 当p节点的值与tail节点的值不同时，将tail的后继指向p，tail后移
         * 5. p指针后移，当p为null时，将tail的后继置为null，返回head
         *
         * @param head 链表头结点
         * @return 去重后的链表头结点
         */
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null)
                return null;
            ListNode tail = head, p = head;
            while (p != null) {
                if (tail.val != p.val) {
                    tail.next = p;
                    tail = tail.next;
                }
                p = p.next;
            }
            tail.next = null;
            return head;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
        // put your test code here

    }
}