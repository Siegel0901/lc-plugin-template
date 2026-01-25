package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

public class RotateList {

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
         * 思路一：先翻转整个链表，再翻转[1,k]和[k+1,n]区间
         * 1. 先统计链表节点个数n
         * 2. 若k大于n，则k求n的余数，则为旋转次数
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @param k    旋转的次数
         * @return 旋转后的链表头结点
         */
//        public ListNode rotateRight(ListNode head, int k) {
//            if (head == null || k == 0)
//                return head;
//
//            // 统计节点个数
//            ListNode p = head;
//            int n = 0;
//            while (p != null) {
//                n++;
//                p = p.next;
//            }
//
//            // 求旋转次数,为0则返回原链表
//            k %= n;
//            if (k == 0)
//                return head;
//
//            // 翻转[1,n],翻转后,head指向链表尾部,reversed指向链表头部
//            ListNode reversed = reverse(head);
//
//            // 找到第k个节点
//            p = new ListNode(-101, reversed);
//            while (p != null && k > 0) {
//                p = p.next;
//                k--;
//            }
//            // 记录第k+1个节点
//            ListNode kNext = p.next;
//            // 断开[1,k]和[k+1,n]
//            p.next = null;
//
//            // 翻转[1,k]，翻转后reversed为尾节点，p为头节点
//            reverse(reversed);
//
//            // 翻转[k+1,n]，翻转后head为头节点，kNext为尾节点
//            reverse(kNext);
//
//            // 拼接两个链表
//            reversed.next = head;
//
//            return p;
//        }
//
//        public ListNode reverse(ListNode head) {
//            ListNode pre = null, cur = head, next;
//            while (cur != null) {
//                next = cur.next;
//                cur.next = pre;
//                pre = cur;
//                cur = next;
//            }
//            return pre;
//        }

        /**
         * 思路二：成环
         * 1. 判断链表节点个数n
         * 2. k对n求余
         * 3. 找到第 n - k 个节点
         * 4. 断开 n - k 与 n - k + 1
         * 5. 返回 n - k + 1
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @param k    旋转的次数
         * @return 旋转后的链表头结点
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0)
                return head;

            ListNode dummy = new ListNode(-101, head), p = dummy;
            // 求链表长度
            int n = 0;
            while (p.next != null) {
                n++;
                p = p.next;
            }
            // 判断移动次数
            k %= n;
            if (k == 0)
                return head;
            // 头尾相连
            p.next = head;
            // 找到第 n - k 个节点
            p = dummy;
            int count = n - k;
            while (p != null && count > 0) {
                p = p.next;
                count--;
            }
            // 让第 n - k + 1 个节点作为头节点
            head = p.next;
            // 断开 n - k 和 n - k + 1
            p.next = null;
            return head;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new RotateList().new Solution();
        // put your test code here
//        ListNode.print(solution.reverse(ListNode.createHead(new int[]{1, 2, 3, 4})));
    }
}