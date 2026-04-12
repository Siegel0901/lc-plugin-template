package leetcode.editor.cn;

import leetcode.editor.common.ListNode;

import java.util.Random;

public class LinkedListRandomNode {

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
    /**
     * 思路：蓄水池抽样算法
     * P(第k个节点被选中)
     * = P(第k步选中) × P(第k+1步不替换) × P(第k+2步不替换) × ... × P(第n步不替换)
     * = (1/k) × [k/(k+1)] × [(k+1)/(k+2)] × ... × [(n-1)/n]
     * = (1/k) × (k/(k+1)) × ((k+1)/(k+2)) × ... × ((n-1)/n)
     * = 1/n  （分子分母相消）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    class Solution {
        ListNode head;
        Random rd;

        public Solution(ListNode head) {
            this.head = head;
            rd = new Random();
        }

        public int getRandom() {
            int i = 0, res = 0;
            ListNode p = head;
            while (p != null) {
                i++;
                // [0,i)这i个元素中选到0的概率为1/i
                if (rd.nextInt(i) == 0)
                    res = p.val;
                p = p.next;
            }
            return res;
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(head);
     * int param_1 = obj.getRandom();
     */
    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
//        Solution solution = new LinkedListRandomNode().new Solution();
        // put your test code here

    }
}