package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

public class ReverseNodesInKGroup {

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
         * 思路一：递归
         * 1. 递归边界，如果链表长度小于k，则直接返回
         * 2. 如果链表长度大于等于k，递归求第 k + 1个节点为首的k个一组翻转链表作为后继
         * 3. 最后求head为首的前K个节点翻转得到最终结果
         * 4. 从递归边界往上看，每次都在求前K个节点翻转的链表
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @param k    组长度
         * @return 新链表头结点
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null)
                return head;

            // 检查并定位到第k个节点
            ListNode kthNode = head;
            for (int i = 1; i < k && kthNode != null; i++)
                kthNode = kthNode.next;

            // 如果节点数不足k个，直接返回
            if (kthNode == null)
                return head;

            // 递归处理后续并翻转当前段
            kthNode.next = reverseKGroup(kthNode.next, k);
            return reverseN(head, k);
        }

        public ListNode reverseN(ListNode head, int n) {
            ListNode pre = null, cur = head;
            while (n > 0 && cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                n--;
            }
            if (head != null)
                head.next = cur;
            return pre;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
        // put your test code here
        ListNode head = ListNode.createHead(new int[]{1, 2, 3, 4, 5});
        ListNode reversed = solution.reverseKGroup(head, 3);
        ListNode.print(reversed);
    }
}