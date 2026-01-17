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
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> set = new HashSet<>();
            ListNode p = headA;
            while (p != null) {
                set.add(p);
                p = p.next;
            }
            p = headB;
            while (p != null) {
                if (set.contains(p))
                    return p;
                set.add(p);
                p = p.next;
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoLinkedLists().new Solution();
        // put your test code here

    }
}