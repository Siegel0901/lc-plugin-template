package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class NextGreaterNodeInLinkedList {

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
         * 思路一：链表转数组 + 单调栈
         * 时间复杂度：O(n)
         * 空间复杂度：O(n)
         *
         * @param head 链表头结点
         * @return 结果数组
         */
        public int[] nextLargerNodes(ListNode head) {
            List<Integer> nums = new ArrayList<>();
            ArrayDeque<Integer> stk = new ArrayDeque<>();
            for (ListNode p = head; p != null; p = p.next)
                nums.add(p.val);
            int n = nums.size();
            int[] res = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                while (!stk.isEmpty() && stk.peek() <= nums.get(i))
                    stk.pop();
                res[i] = stk.isEmpty() ? 0 : stk.peek();
                stk.push(nums.get(i));
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new NextGreaterNodeInLinkedList().new Solution();
        // put your test code here

    }
}