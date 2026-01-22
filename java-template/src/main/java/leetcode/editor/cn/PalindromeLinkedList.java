package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class PalindromeLinkedList {

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
         * 思路一：逆序输出到容器，一一对比
         * 1. 将元素输入至栈中
         * 2. 栈顶元素依次出栈，同时遍历链表
         * 3. 判断栈顶是否跟链表当前节点相等
         * 4. 若存在不相等的节点，则不是回文链表
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @return 是否是回文链表
         */
//        public boolean isPalindrome(ListNode head) {
//            LinkedList<Integer> stack = new LinkedList<>();
//            ListNode p;
//            for (p = head; p != null; p = p.next)
//                stack.push(p.val);
//            p = head;
//            while (!stack.isEmpty()) {
//                if (stack.pop() != p.val)
//                    return false;
//                p = p.next;
//            }
//            return true;
//        }

        /**
         * 思路二：后序遍历链表（递归实现栈）
         * 1. 通过递归的方式后序遍历链表
         * 2. 遍历过程中与正序遍历的指针节点值对比
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)（递归深度）
         *
         * @param head 链表头结点
         * @return 是否是回文链表
         */
//        public boolean isPalindrome(ListNode head) {
//            left = head;
//            traverse(head);
//            return res;
//        }
//
//        public ListNode left, right;
//        public boolean res = true;
//
//        public void traverse(ListNode right) {
//            if (right == null) return;
//            traverse(right.next);
//            if (right.val != left.val)
//                res = false;
//            left = left.next;
//        }

        /**
         * 思路三：原地翻转部分链表
         * 1. 先用快慢指针定位到链表中点（节点个数为偶数时，slow是中间靠后的节点）
         * 2. 从slow开始翻转链表
         * 3. head和slow同步后移比较节点
         * 4. 若不一致，则不是回文链表
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         *
         * @param head 链表头结点
         * @return 是否是回文链表
         */
        public boolean isPalindrome(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode left = head;
            ListNode right = reverse(slow);
            while (right != null) {
                if (right.val != left.val)
                    return false;
                right = right.next;
                left = left.next;
            }
            return true;
        }

        /**
         * 链表翻转
         * 1(cur) -> 2(next) -> null(pre)
         * null(pre) <- 1(cur) -> 2(next) -> null(pre) [cur.next = pre]
         * null <- 1(pre) -> 2(cur) -> null(next) [cur,pre后移]
         * null <- 1(pre) <- 2(cur) -> null(next) [cur.next = pre]
         * null <- 1 <- 2(pre) -> null(cur) [cur,pre后移]
         * 最后返回pre作为头节点
         *
         * @param head 链表头结点
         * @return 新链表头结点
         */
        public ListNode reverse(ListNode head) {
            // pre为原来的前驱节点，cur为当前节点
            ListNode pre = null, cur = head;
            // cur往后移至null遍历完链表，循环结束
            while (cur != null) {
                // 记录cur的后继
                ListNode next = cur.next;
                // 翻转链表即把当前节点的后继指向原来的前驱
                cur.next = pre;
                // 原来的前驱后移
                pre = cur;
                // 当前节点后移
                cur = next;
            }
            return pre;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList().new Solution();
        // put your test code here
        ListNode head = ListNode.createHead(new int[]{1, 2, 3, 4});
//        boolean palindrome = solution.isPalindrome(head);
//        System.out.println(palindrome);
//        solution.traverse(head);
    }
}