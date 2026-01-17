package leetcode.editor.cn;

import java.util.*;

import leetcode.editor.common.*;

public class MergeKSortedLists {

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
        public ListNode mergeKLists(ListNode[] lists) {
            List<ListNode> listNodes = new ArrayList<>();
            for (ListNode list : lists) {
                ListNode p = list;
                while (p != null) {
                    ListNode temp = p;
                    p = p.next;
                    temp.next = null;
                    listNodes.add(temp);
                }
            }
            listNodes.sort(Comparator.comparingInt(l -> l.val));
            ListIterator<ListNode> it = listNodes.listIterator();
            ListNode head = new ListNode(0);
            ListNode tail = head;
            while (it.hasNext()) {
                tail.next = it.next();
                tail = tail.next;
            }
            return head.next;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
        // put your test code here

    }
}